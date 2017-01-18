#include <camkes.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sel4/sel4.h>
#include <sel4/benchmark_utilisation_types.h>

#define CAP_BEGIN 0xd
#define CAP_END 0x56
#define NUM_CAPS CAP_END - CAP_BEGIN + 1

struct info {
    unsigned cap;
    uint64_t util;
    char name[80];
};
typedef struct info info_t;

static info_t info[NUM_CAPS];

static int info_compare(const void *v1, const void *v2) {
    info_t *i1 = (info_t*) v1;
    info_t *i2 = (info_t*) v2;
    if (i1->util < i2->util) {
        return 1;
    } else if (i1->util > i2->util) {
        return -1;
    } else {
        return 0;
    }
}

static void reset() {
    for (int i = 0; i < NUM_CAPS; i++) {
        seL4_BenchmarkResetThreadUtilisation(i + CAP_BEGIN);
    }
    seL4_BenchmarkResetLog();
}

static void report() {
    uint64_t *ipcbuffer = (uint64_t *) &(seL4_GetIPCBuffer()->msg[0]);

    for (int i = 0; i < NUM_CAPS; i++) {
        info[i].cap = i + CAP_BEGIN;
        seL4_BenchmarkGetThreadUtilisation(i + CAP_BEGIN);
        info[i].util = ipcbuffer[BENCHMARK_TCB_UTILISATION];
        strlcpy(info[i].name, (char *) &ipcbuffer[1], 80);
    }

    qsort(info, NUM_CAPS, sizeof(info_t), &info_compare);

    uint64_t total = 0;
    for (int i = 0; i < NUM_CAPS; i++) {
        total += info[i].util;
    }

    printf("\n");
    for (int i = 0; i < NUM_CAPS; i++) {
        double p = 100.0 * info[i].util / total;
        if (p >= 0.1) {
            printf("%0.1f%%\t\t%s (cap 0x%x)\n", 100.0 * info[i].util / total, info[i].name, info[i].cap);
        }
    }
    printf("\n");

    reset();
    report_reg_callback(&report, NULL);
}

int run() {
    reset();
    report_reg_callback(&report, NULL);
    return 0;
}
