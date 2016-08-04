#ifndef UBX_H
#define UBX_H

#include <stdint.h>

/**********************************************************************
    NAV
**********************************************************************/

#define UBX_nav_class 0x01


/**********************************************************************
    NAV-SOL
**********************************************************************/

#define UBX_nav_sol_id 0x06
#define UBX_nav_sol_length 52

struct UBX_nav_sol {
    uint32_t iTOW;
    int32_t fTOW;
    int16_t week;
    uint8_t gpsFix;
    uint8_t flags;
    int32_t ecefX;
    int32_t ecefY;
    int32_t ecefZ;
    uint32_t pAcc;
    int32_t ecefVX;
    int32_t ecefVY;
    int32_t ecefVZ;
    uint32_t sAcc;
    uint16_t pDOP;
    uint8_t reserved1;
    uint8_t numSV;
    uint32_t reserved2;
};

char *GPS_FIX_TEXT[] = {
    "No Fix",
    "Dead Reckoning only",
    "2D-Fix",
    "3D-Fix",
    "GPS + dead reckoning combined",
    "Time only fix"
};

/**********************************************************************
    NAV-POSLLH
**********************************************************************/

#define UBX_nav_posllh_id 0x02
#define UBX_nav_posllh_length 28

struct UBX_nav_posllh {
    uint32_t iTOW;
    int32_t lon;
    int32_t lat;
    int32_t height;
    int32_t hMSL;
    uint32_t hAcc;
    uint32_t vAcc;
};


/**********************************************************************
    UBX general
**********************************************************************/

union UBX_payload {
    struct UBX_nav_sol nav_sol;
    struct UBX_nav_posllh nav_posllh;
};

#define UBX_sync1 0xB5
#define UBX_sync2 0x62

#endif // UBX_H
