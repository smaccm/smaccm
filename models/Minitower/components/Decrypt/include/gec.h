#ifndef _GEC_H
#define _GEC_H

/* GEC specific config: use refhash. Defined here in the source because tower
 * build system doesn't support adding -D cflags options. */
#ifndef ED25519_REFHASH
#define ED25519_REFHASH
#endif

#include "aes.h"
#include "gcm.h"
#include "curve25519-donna.h"
#include "ed25519.h"
#include "ed25519-hash.h"

#define _GEC_SALT_LEN 8
#define _GEC_CTR_LEN 4
#define _GEC_TAG_LEN 12            /* 8 byte GCM tags */
#define _GEC_SYM_CIPHER_KEY_LEN 16 /* AES 128, hardcoded, should fix */
#define _CTR_IV_LEN 16
#define _GCM_IV_LEN 12

#if (_GCM_IV_LEN != _GEC_SALT_LEN + _GEC_CTR_LEN)
#error "GCM IV Len should be equal to salt plus counter."
#endif

#define GEC_RAW_KEY_LEN (_GEC_SALT_LEN + _GEC_SYM_CIPHER_KEY_LEN)
#define GEC_PT_LEN      80        /* size drivine by HACMS needs */
#define GEC_CT_LEN (_GEC_CTR_LEN + GEC_PT_LEN + _GEC_TAG_LEN)

#define GEC_SIG_LEN 64          /* XXX floodberry's code uses a constant, no other CPP to leverage */
#define GEC_PUB_KEY_LEN  32                     /* from ed25519-donna */
#define GEC_PRIV_KEY_LEN 32                     /* from ed25519-donna*/
#define GEC_HASH_LEN HASH_DIGEST_SIZE           /* from ed25519-donna */
#define GEC_SIGNED_MSG_LEN (GEC_PUB_EPHEMERAL_KEY_LEN * 2) /* XXX Abstraction breakage.  We can either have static sizes or a good API, pick one */

#define GEC_PUB_EPHEMERAL_KEY_LEN  32           /* from curve25519-donna */
#define GEC_PRIV_EPHEMERAL_KEY_LEN 32           /* from curve25519-donna*/
#define GEC_SECRET_BYTES_LEN 32                 /* from curve25519_donna */

#define GEC_SUCCESS 0
#define GEC_ERROR_INVALID 1
#define GEC_ERROR_DUPLICATE_COUNTER 2
#define GEC_ERROR_DECRYPT_AUTH_FAILED 3
#define GEC_ERROR_COUNTER_ROLLOVER 4
#define GEC_ERROR_ENCRYPT_AUTH_FAILED 5

// #define SUFFIX _LOW_LEVEL
// #define GEC_FN(fn) fn##SUFFIX
#define GEC_FN(fn) fn

#define crypto_hash ed25519_hash

struct gec_sym_key {
    gcm_ctx  gctx;
    uint8_t  salt[_GEC_SALT_LEN];
    uint64_t ctr;
};

/* A symmetric key structure for confidentiallity only encryption */
struct gec_sym_key_conf {
    aes_encrypt_ctx cctx;
    uint8_t         salt[_GEC_SALT_LEN];
    uint64_t        ctr;
};

struct gec_privkey {
    ed25519_secret_key priv;
    ed25519_public_key pub;
};

struct gec_pubkey {
    ed25519_public_key pub;
};

void GEC_FN(gec_mk_privkey)(struct gec_privkey *k, const uint8_t privkey[GEC_PRIV_KEY_LEN], const uint8_t pubkey[GEC_PUB_KEY_LEN]);
void GEC_FN(gec_mk_pubkey)(struct gec_pubkey *k, const uint8_t pubkey[GEC_PUB_KEY_LEN]);

void GEC_FN(gec_init_sym_key_conf_auth)(struct gec_sym_key *k, const uint8_t rawkey[GEC_RAW_KEY_LEN]);
void GEC_FN(gec_init_sym_key_conf)(struct gec_sym_key_conf *k, const uint8_t rawkey[GEC_RAW_KEY_LEN]);
void GEC_FN(gec_key_material_to_2_channels)(struct gec_sym_key *chan1, struct gec_sym_key *chan2
                                          , const uint8_t key_material[2*GEC_RAW_KEY_LEN]);
void GEC_FN(gec_clear)(struct gec_sym_key *k);
void GEC_FN(gec_clear_conf)(struct gec_sym_key_conf *k);

// Authenticated encryption mode over statically sized messages.
int GEC_FN(gec_encrypt)(struct gec_sym_key *k, const uint8_t pt[GEC_PT_LEN], uint8_t ct[GEC_CT_LEN]);
int GEC_FN(gec_decrypt)(struct gec_sym_key *k, const uint8_t ct[GEC_CT_LEN], uint8_t pt[GEC_PT_LEN]);

int GEC_FN(gec_encrypt_conf)(struct gec_sym_key_conf *k, const uint8_t *pt, uint8_t *ct, size_t len);
int GEC_FN(gec_decrypt_conf)(struct gec_sym_key_conf *k, const uint8_t *ct, uint8_t *pt, size_t len);

// Given random bytes in the privkey, construct a private and public key pair.
void GEC_FN(gec_generate_sign_keypair)(struct gec_privkey *q, struct gec_pubkey *p);

// Given a private key and a message, create a signature.
void GEC_FN(gec_sign)(const struct gec_privkey *k, const uint8_t *msg, const size_t msg_len, uint8_t sig[GEC_SIG_LEN]);
// Given a public key, message and tag (signature) return 0 if the signature is correct, non-zero otherwize
int GEC_FN(gec_verify)(const struct gec_pubkey *k, const uint8_t *msg, const size_t msg_len, const uint8_t sig[GEC_SIG_LEN]);

// Input random GEC_PRIV_KEY_LEN bytes and compute the matching public key.
void GEC_FN(gec_generate_ephemeral_keypair)(const uint8_t gec_ephemeral_priv[GEC_PRIV_EPHEMERAL_KEY_LEN], uint8_t gec_ephemeral_pub[GEC_PUB_EPHEMERAL_KEY_LEN]);

// Given a public and private ephemeral keys, compute a shared secret.  That is, `secret_bytes = Hash(dh(pub,priv))`
void GEC_FN(gec_ecdh)(uint8_t secret_bytes[GEC_SECRET_BYTES_LEN], const uint8_t gec_ephemeral_priv[GEC_PRIV_EPHEMERAL_KEY_LEN], const uint8_t gec_ephemeral_pub[GEC_PUB_EPHEMERAL_KEY_LEN]);

// Compute a hash of the input.
void GEC_FN(gec_hash)(const uint8_t *input, size_t input_len, uint8_t digest[GEC_HASH_LEN]);

#endif /*  _GEC_H */
