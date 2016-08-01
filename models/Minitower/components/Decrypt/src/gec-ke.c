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

#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include "gec-ke.h"
#include "gec.h"

void clear_ctx(gec_sts_ctx_t * ctx)
{
     memset(&ctx->theirKCK, 0, GEC_RAW_KEY_LEN);
     memset(&ctx->myKCK, 0, GEC_RAW_KEY_LEN);
     memset(ctx->client_key_material, 0, KEY_MATERIAL_LEN);
     memset(&ctx->myPrivateKey_ephemeral, 0, EPHEMERAL_PRIVATEKEY_LEN);
     memset(&ctx->myPublicKey_ephemeral, 0, EPHEMERAL_PUBLICKEY_LEN);
     memset(&ctx->theirPublicKey_ephemeral, 0, EPHEMERAL_PUBLICKEY_LEN);
     memset(&ctx->theirPublicKey, 0, PUBLICKEY_LEN);
     memset(&ctx->myPublicKey, 0, PUBLICKEY_LEN);
     memset(&ctx->myPrivateKey, 0, PRIVATEKEY_LEN);
     ctx->protocol_stage = INVALID_STAGE;
     ctx->party = INVALID_PARTY;
}

void reset_ctx(gec_sts_ctx_t * ctx)
{
     memset(&ctx->theirKCK, 0, GEC_RAW_KEY_LEN);
     memset(&ctx->myKCK, 0, GEC_RAW_KEY_LEN);
     memset(ctx->client_key_material, 0, KEY_MATERIAL_LEN);
     memset(&ctx->myPrivateKey_ephemeral, 0, EPHEMERAL_PRIVATEKEY_LEN);
     memset(&ctx->myPublicKey_ephemeral, 0, EPHEMERAL_PUBLICKEY_LEN);
     memset(&ctx->theirPublicKey_ephemeral, 0, EPHEMERAL_PUBLICKEY_LEN);
     ctx->protocol_stage = READY_STAGE;
     ctx->party = INVALID_PARTY;
}

#define PUT16be(ptr, cnt) { (((uint8_t *)ptr)[1] = (cnt) & 0xFF) ; \
                            (((uint8_t *)ptr)[0] = ((cnt) >> 8) & 0xFF ); }
#define KDF_INPUT_LEN (sizeof(count) + crypto_scalarmult_BYTES + sizeof(party_byte))
static void kdf( uint8_t *result
               , uint32_t result_size
               , const uint8_t secret[GEC_SECRET_BYTES_LEN]
               , uint8_t party_byte)
{
    uint16_t count;
    uint32_t bytes_remaining = result_size;
    const uint32_t HASH_INPUT_LEN = sizeof(count) + GEC_SECRET_BYTES_LEN + sizeof(party_byte);
    uint8_t hash_input[HASH_INPUT_LEN];
    uint32_t nrReps = (result_size + GEC_HASH_LEN - 1) / GEC_HASH_LEN;

    hash_input[sizeof(hash_input)-1] = party_byte;
    memcpy(hash_input + sizeof(count), secret, GEC_SECRET_BYTES_LEN);
    for(count=0; count < nrReps; count++) {
        PUT16be(hash_input, count);
        if(bytes_remaining > GEC_HASH_LEN) {
            crypto_hash(result + count*GEC_HASH_LEN, hash_input, HASH_INPUT_LEN);
        } else {
            uint8_t tmp[GEC_HASH_LEN];
            crypto_hash(tmp, hash_input, HASH_INPUT_LEN);
            memcpy(result + count*GEC_HASH_LEN, tmp, bytes_remaining);
        }
        bytes_remaining -= GEC_HASH_LEN;
    }
}

static void derive_key_materials(gec_sts_ctx_t *ctx, uint8_t secret[GEC_SECRET_BYTES_LEN])
{
    uint8_t tmp[GEC_RAW_KEY_LEN];


    kdf(tmp, GEC_RAW_KEY_LEN, secret, ctx->party);
    gec_init_sym_key_conf(&ctx->myKCK, tmp);
    memset(tmp, 0, GEC_RAW_KEY_LEN);
    kdf(tmp, GEC_RAW_KEY_LEN, secret, THEIR_PARTY(ctx));
    gec_init_sym_key_conf(&ctx->theirKCK, tmp);
    memset(tmp, 0, GEC_RAW_KEY_LEN);
    kdf(ctx->client_key_material, KEY_MATERIAL_LEN, secret, CLIENT);
}

static int verify(gec_sts_ctx_t *ctx, const uint8_t msg[AUTH_DATA_LEN])
{
    uint8_t sig[AUTH_DATA_LEN];
    uint8_t signed_contents[2*EPHEMERAL_PUBLICKEY_LEN];
    unsigned long long unsiglen = 2 * EPHEMERAL_PUBLICKEY_LEN;

    memcpy(signed_contents, ctx->theirPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);
    memcpy(signed_contents + EPHEMERAL_PUBLICKEY_LEN, ctx->myPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);

    memset(sig,0, sizeof(sig));
    gec_decrypt_conf(&ctx->theirKCK, msg, sig, sizeof(sig));
    return gec_verify(&ctx->theirPublicKey, signed_contents, unsiglen, sig);
}

// Generate private and public key pairs for future use.
// The first 32 bytes of 'sk' must be random values!
void generate(struct gec_pubkey *pk, struct gec_privkey *sk, const uint8_t random_data[RANDOM_DATA_LEN])
{
    memcpy(sk->priv,random_data,RANDOM_DATA_LEN);
    gec_generate_sign_keypair(sk,pk);
}

// Contexts are populated with long-term keys for the current system (public and private).
void init_context( gec_sts_ctx_t *ctx
                 , const struct gec_pubkey *myPublicKey
                 , const struct gec_privkey *myPrivateKey
                 , const struct gec_pubkey *theirPublicKey)
{
    ctx->protocol_stage = READY_STAGE;
    ctx->party = INVALID_PARTY;
    memcpy(&ctx->myPublicKey, myPublicKey, sizeof(struct gec_pubkey));
    memcpy(&ctx->myPrivateKey, myPrivateKey, sizeof(struct gec_privkey));
    memcpy(&ctx->theirPublicKey, theirPublicKey, sizeof(struct gec_pubkey));
}

// Reset the context and set a new partner public key.
//
// Context is useful only when given the public keys for the ONE anticipated communication partner.
void reset_partner(gec_sts_ctx_t *ctx, const struct gec_pubkey *theirPublicKey)
{
    memcpy(&ctx->theirPublicKey, theirPublicKey, sizeof(struct gec_pubkey));
    reset_ctx(ctx);
}

/******************************************************************************/
/************ Consume and produce messages 1-3 in the STS protocol ************/
/******************************************************************************/

// Party A's step 1
// 1) Generate a random ephemeral public and private key pair. (ctx ~ privA_e, msg1 ~ publicA_e)
int initiate_sts(uint8_t msg1[MSG_1_LEN], gec_sts_ctx_t *ctx, const uint8_t random_data[RANDOM_DATA_LEN])      // Party 'A'
{
    int ret = -1;
    if(READY_STAGE == ctx->protocol_stage) {
        memcpy(ctx->myPrivateKey_ephemeral, random_data, RANDOM_DATA_LEN);
        gec_generate_ephemeral_keypair( ctx->myPrivateKey_ephemeral
                                      , ctx->myPublicKey_ephemeral);

        memcpy(msg1, ctx->myPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);
        ctx->protocol_stage = MESSAGE_1_DONE;
        ctx->party = INITIATOR;
        ret = 0;
    }
    return ret;
}

// Party B's step 1
// 2) Generate a random ephemeral public and private key pair.
//    Compute the shared secrets:   kdf( ecdh(pubA_e, privB_e) )
//    Construct the response:      msg2 = pubB_e || E_k1( sign_privB(pubA_e, pubB_e), pubA_e, pubB_e )
int respond_sts( const uint8_t msg1[MSG_1_LEN]
               , uint8_t msg2[MSG_2_LEN]
               , gec_sts_ctx_t *ctx                               // Party 'B'
               , const uint8_t random_data[RANDOM_DATA_LEN])
{
    uint8_t unsigned_data[2 * EPHEMERAL_PUBLICKEY_LEN];
    uint8_t z[GEC_SECRET_BYTES_LEN];
    uint8_t concatKeysSig[2*EPHEMERAL_PUBLICKEY_LEN + GEC_SIG_LEN];
    uint8_t sig[GEC_SIG_LEN];
    int ret = -1;

    if(READY_STAGE == ctx->protocol_stage) {
        ctx->party = RESPONDER;

        // Generate ephemeral keys and populate CTX
        memcpy(ctx->myPrivateKey_ephemeral, random_data, RANDOM_DATA_LEN);
        gec_generate_ephemeral_keypair( ctx->myPrivateKey_ephemeral
                                      , ctx->myPublicKey_ephemeral);
        memcpy(ctx->theirPublicKey_ephemeral, msg1, EPHEMERAL_PUBLICKEY_LEN);

        // Derive shared secret KDF( ECDH(Priv_B,PubA_E) )
        gec_ecdh(z, ctx->theirPublicKey_ephemeral, ctx->myPrivateKey_ephemeral);
        derive_key_materials(ctx, z);

        // Construct response [ PubB | E_k1 ( Sign_PubB ( PubB_E | PubA_E ), pubB_E, pubA_E ) ]
        memcpy(msg2, ctx->myPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);

        memcpy(unsigned_data, ctx->myPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);
        memcpy(unsigned_data + EPHEMERAL_PUBLICKEY_LEN, ctx->theirPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);
        gec_sign(&ctx->myPrivateKey, unsigned_data, sizeof(unsigned_data), sig);

        gec_encrypt_conf(&ctx->myKCK, sig, msg2 + EPHEMERAL_PUBLICKEY_LEN, GEC_SIG_LEN);

        ctx->protocol_stage = MESSAGE_2_DONE;
        ret = 0;
    }
    return ret;
}

// Party A's step 2
// 3) Compute the shared secret:  kdf( ecdh(pubB_e, privA_e) )
//    Verify msg2:                verify_pubB(D_k1(msg2)) &&  open(D_k1(msg2)) == (pubA_e, pubB_e)
//    Construct the response:     msg3 = E_k1( sign_privA(pubB_e, pubA_e) )
//    Set the key material:       key_material = some part of kdf
int response_ack_sts( const uint8_t msg2[MSG_2_LEN]
                    , uint8_t msg3[MSG_3_LEN]
                    , gec_sts_ctx_t *ctx
                    , uint8_t key_material[KEY_MATERIAL_LEN])         // Party 'A'
{
    uint8_t z[GEC_SECRET_BYTES_LEN];
    int sig_result;
    int ret = -1;

    if(MESSAGE_1_DONE == ctx->protocol_stage) {
        // Derive shared secret KDF( ECDH(Priv_B,PubA_E) )
        memcpy(ctx->theirPublicKey_ephemeral, msg2, EPHEMERAL_PUBLICKEY_LEN);

        gec_ecdh(z, ctx->theirPublicKey_ephemeral, ctx->myPrivateKey_ephemeral);
        derive_key_materials(ctx, z);

        // verify msg2
        sig_result = verify(ctx, msg2 + EPHEMERAL_PUBLICKEY_LEN);
        if(sig_result != 0) {
            ret = -1;
        } else {
            // Construct response [ E_k2 ( Sign_PubA ( PubA_E | PubB_E ) | PubA_E | PubB_E ) ]
            uint8_t unsigned_data[EPHEMERAL_PUBLICKEY_LEN * 2];
            uint8_t concatKeysSig[2*EPHEMERAL_PUBLICKEY_LEN + GEC_SIG_LEN];
            uint8_t sig[GEC_SIG_LEN];

            memcpy(unsigned_data, ctx->myPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);
            memcpy(unsigned_data + EPHEMERAL_PUBLICKEY_LEN, ctx->theirPublicKey_ephemeral, EPHEMERAL_PUBLICKEY_LEN);
            gec_sign(&ctx->myPrivateKey, unsigned_data, sizeof(unsigned_data), sig);

            gec_encrypt_conf(&ctx->myKCK, sig, msg3, GEC_SIG_LEN);

            memcpy(key_material, ctx->client_key_material, KEY_MATERIAL_LEN);
            reset_ctx(ctx);
            ret = 0;
        }
    }
    return ret;
}

// Party B's step 2
// 4) Verify msg3:                verify_pubA(D_k1(msg3)) &&  open(D_k1(msg3)) == (pubB_e, pubA_e)
//    Set the key material:       key_material = k2
int finish_sts( const uint8_t msg3[MSG_3_LEN]                          // Party 'B'
              , gec_sts_ctx_t *ctx
              , uint8_t key_material[KEY_MATERIAL_LEN])
{
    int ret=-1;

    if(MESSAGE_2_DONE == ctx->protocol_stage) {
        if(verify(ctx, msg3) != 0) {
            ret = -1;
        } else {
            memcpy(key_material, ctx->client_key_material, KEY_MATERIAL_LEN);
            reset_ctx(ctx);
            ret = 0;
        }
    }
    return ret;
}
