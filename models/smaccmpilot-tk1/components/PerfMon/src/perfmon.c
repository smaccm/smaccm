#include <camkes.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sel4/sel4.h>
#include <sel4/benchmark_utilisation_types.h>
#include <all_tcb_caps.h>

struct info {
    unsigned cap;
    uint64_t util;
};
typedef struct info info_t;

static info_t info[ALL_TCB_CAPS_NUM];

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
    for (int i = 0; i < ALL_TCB_CAPS_NUM; i++) {
        seL4_BenchmarkResetThreadUtilisation(i + ALL_TCB_CAPS_MIN);
    }
    seL4_BenchmarkResetLog();
}

static void report(void *ignore) {
    uint64_t *ipcbuffer = (uint64_t *) &(seL4_GetIPCBuffer()->msg[0]);

    for (int i = 0; i < ALL_TCB_CAPS_NUM; i++) {
        info[i].cap = i + ALL_TCB_CAPS_MIN;
        seL4_BenchmarkGetThreadUtilisation(info[i].cap);
        info[i].util = ipcbuffer[BENCHMARK_TCB_UTILISATION];
    }

    qsort(info, ALL_TCB_CAPS_NUM, sizeof(info_t), &info_compare);

    uint64_t total = 0;
    for (int i = 0; i < ALL_TCB_CAPS_NUM; i++) {
        total += info[i].util;
    }

    printf("\n");
    for (int i = 0; i < ALL_TCB_CAPS_NUM; i++) {
        double p = 100.0 * info[i].util / total;
        if (p >= 0.1) {
            printf("%0.1f%%\t\t%s\n", p, cap_names[info[i].cap]);
        }
    }
    printf("\n");

    reset();
    report_reg_callback(&report, NULL);
}

void report__init(void) {
    reset();
    report_reg_callback(&report, NULL);
}
