#ifndef __BUFFER__H
#define __BUFFER__H 

#include <stdint.h>
#include <string.h>

typedef struct circ_buff_t{
  void * pBegin;
  void * pEnd;
  void * pBuff;
  size_t cap;
  size_t size;
  size_t count;
} circ_buff_t;


static inline void init_circ_buff(circ_buff_t * pCircBuff, void * pBuff, size_t size, size_t cap){
  pCircBuff->pBuff = pBuff;
  pCircBuff->size = size;
  pCircBuff->cap = cap;
  pCircBuff->count = 0;
  pCircBuff->pBegin = pCircBuff->pBuff;
  pCircBuff->pEnd = pCircBuff->pBuff;
}

static inline void * incr_wrap(void * pLoc, circ_buff_t * pBuff){
  if(pLoc + pBuff->size == (pBuff->pBuff + (pBuff->size * pBuff->cap))){
      return pBuff->pBuff;
  }
  return pLoc + pBuff->size;
}

static inline uint8_t add_to_circ_buff(circ_buff_t * pBuff, void * pItem){
  
  if(pBuff->cap == pBuff->count){
    return 1;
  }

  memcpy(pBuff->pEnd, pItem, pBuff->size);
  pBuff->pEnd = incr_wrap(pBuff->pEnd, pBuff);
  pBuff->count++;
  return 0;
}


static inline uint8_t remove_from_circ_buff(circ_buff_t * pBuff, void * dest){

  if(pBuff->count == 0){
    return 1;
  }

  memcpy(dest, pBuff->pBegin, pBuff->size);
  pBuff->pBegin = incr_wrap(pBuff->pBegin, pBuff);
  pBuff->count--;

  return 0;
}

//#define BUFF_SIZE 64
//
//uint32_t buff[BUFF_SIZE];
//circ_buff_t circ_buff;
//
//int main(int argc, char ** argv){
//
//  init_circ_buff(&circ_buff, &buff, sizeof(uint32_t), BUFF_SIZE);
//
//  uint32_t i;
//  uint32_t item;
//  
//  for(i = 0; i < BUFF_SIZE; i++){
//    add_to_circ_buff(&circ_buff, &i);
//  }
//
//  for(i = 0; i < BUFF_SIZE/2; i++){
//    remove_from_circ_buff(&circ_buff, &item);
//    printf("%d\n", item);
//  }
//
//  for(i = 0; i < BUFF_SIZE/2; i++){
//    add_to_circ_buff(&circ_buff, &i);
//  }
//
//  for(i = 0; i < BUFF_SIZE; i++){
//    remove_from_circ_buff(&circ_buff, &item);
//    printf("%d\n", item);
//  }
//
//  return 0;
//}



#endif
