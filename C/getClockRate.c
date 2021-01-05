//
//  getClockRate.c
//  Computer system
//
//  Created by liufapeng on 2020/12/20.
//  Copyright © 2020 liufapeng. All rights reserved.
//

#include "getClockRate.h"

RCC_ClocksTypeDef get_rcc_clock;

int main() {
    RCC_GetClocksFreq(&get_rcc_clock);
    //todo
}
