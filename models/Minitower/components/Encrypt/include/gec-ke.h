/* An API for a bare-bones implementation of STS with ed25519 DSA signatures
 * for authentication.
 *
 * Assuming a starting point where parties A and B each have public and private keys (pubA, pubB, privA, privB).
 * Each party has already shared, via an out-of-band mechanism, their public keys.
 *
 * A ---> B      A generates an ephemeral pair and sends the public key, pubA_e)
 * A <--- B      B generates an ephemeral pair, computes the shared secret, and sends:  pubB_e || E_k(sign_privB(pubA_e,pubB_e)
 * A ---> B      A generates the shared secret, valids the message, sends E_k(sign_privA(pubB_e, pubA_e))
 *               A and B return k_2, the second 32 bytes from kdf(ecdh(pubA_e,privB_e))
 */
#ifndef _GEC_KE_H
#define _GEC_KE_H

#include <stdint.h>
#include "gec.h"

#define KEY_MATERIAL_LEN (2*GEC_RAW_KEY_LEN)

#define PUBLICKEY_LEN GEC_PUB_KEY_LEN
#define PRIVATEKEY_LEN GEC_PRIV_KEY_LEN

#define EPHEMERAL_PUBLICKEY_LEN GEC_PUB_EPHEMERAL_KEY_LEN
#define EPHEMERAL_PRIVATEKEY_LEN GEC_PRIV_EPHEMERAL_KEY_LEN

#define AUTH_DATA_LEN GEC_SIG_LEN

#define MSG_1_LEN EPHEMERAL_PUBLICKEY_LEN
#define MSG_2_LEN (EPHEMERAL_PUBLICKEY_LEN + AUTH_DATA_LEN)
#define MSG_3_LEN AUTH_DATA_LEN

#define RANDOM_DATA_LEN 32

#define THEIR_PARTY(ctx) (((ctx)->party == INITIATOR) ? RESPONDER : INITIATOR)


typedef enum {
    INVALID_STAGE,
    READY_STAGE,                // Indicates the context is ready for use.
                                //  - Set after init_context.
                                //  - Set after constructing message 3 (party A, response_ack_sts()).
                                //  - Set after receiving a valid message 3 (party B, finish_sts()).
                                //  - Set after reset_partner
    MESSAGE_1_DONE,             // Indicates context ready to receive message 2.
                                //  - Set after constructing message 1 (party A, initiate_sts())
    MESSAGE_2_DONE              // Indicates context if ready to receive message 3
                                //  - Set after constructing message 2 (party B, respond_sts())
} stage_t;

typedef enum {
    INITIATOR, RESPONDER, CLIENT, INVALID_PARTY
} party_t;

// Intermediate data structure containing information relating to the stage of
// the STS protocol.

struct gec_sts_ctx {
    struct gec_pubkey myPublicKey;
    struct gec_privkey myPrivateKey;
    struct gec_pubkey theirPublicKey;
    uint8_t myPublicKey_ephemeral[EPHEMERAL_PUBLICKEY_LEN];
    uint8_t myPrivateKey_ephemeral[EPHEMERAL_PRIVATEKEY_LEN];
    uint8_t theirPublicKey_ephemeral[EPHEMERAL_PUBLICKEY_LEN];
    struct gec_sym_key_conf myKCK;
    struct gec_sym_key_conf theirKCK;
    uint8_t client_key_material[KEY_MATERIAL_LEN];
    stage_t protocol_stage;
    party_t party;
};

typedef struct gec_sts_ctx gec_sts_ctx_t;

// Generate private and public key pairs for future use.
void generate(struct gec_pubkey *pk, struct gec_privkey *sk, const uint8_t random_data[RANDOM_DATA_LEN]);

// Populate a context with long-term keys for the current system (public and
// private) and the public key of a communication partner.
void init_context( gec_sts_ctx_t *ctx
                 , const struct gec_pubkey *myPublicKey
                 , const struct gec_privkey *myPrivateKey
                 , const struct gec_pubkey *theirPublicKey);

// Re-purpose the context for a new public key and set to READY_STAGE.
void reset_partner(gec_sts_ctx_t *ctx, const struct gec_pubkey *theirPublicKey);

// Zero the protocol stage.  This is like reset_patner(), but the current
// parnter public key is retained.
void reset_ctx(gec_sts_ctx_t * ctx);

// Zero all fields, including the long term public and private keys.
void clear_ctx(gec_sts_ctx_t * ctx);

/******************************************************************************/
/************ Consume and produce messages 1-3 in the STS protocol ************/
/******************************************************************************/

// Party A's step 1
// 1) Generate a random ephemeral public and private key pair. (ctx ~ privA_e, msg1 ~ publicA_e)
int initiate_sts(uint8_t msg1[MSG_1_LEN], gec_sts_ctx_t *ctx, const uint8_t random_data[GEC_PRIV_KEY_LEN]);

// Party B's step 1
// 2) Generate a random ephemeral public and private key pair.
//    Compute the shared secrets k1, k2, and k3 from *_e.
//    Construct the response:      msg2 = E_k2( sign_privB(pubA_e, pubB_e) )
int respond_sts( const uint8_t msg1[MSG_1_LEN]
               , uint8_t msg2[MSG_2_LEN]
               , gec_sts_ctx_t *ctx                               // Party 'B'
               , const uint8_t random_data[GEC_PRIV_KEY_LEN]);
// Party A's step 2
// 3) Compute the shared secrets.
//    Verify msg2:                verify_pubB(D_k2(msg2)) &&  open(D_k2(msg2)) == (pubA_e, pubB_e)
//    Construct the response:     msg3 = E_k1( sign_privA(pubB_e, pubA_e) )
//    Set the key material:       key_material = k3
int response_ack_sts( const uint8_t msg2[MSG_2_LEN]
                    , uint8_t msg3[MSG_3_LEN]
                    , gec_sts_ctx_t *ctx
                    , uint8_t key_material[KEY_MATERIAL_LEN]);         // Party 'A'

// Party B's step 2
// 4) Verify msg3:                verify_pubA(D_k1(msg3)) &&  open(D_k1(msg3)) == (pubB_e, pubA_e)
//    Set the key material:       key_material = k3
int finish_sts( const uint8_t msg3[MSG_3_LEN]                          // Party 'B'
              , gec_sts_ctx_t *ctx
              , uint8_t key_material[KEY_MATERIAL_LEN]);

#endif /* _COMMSECKE_H */
