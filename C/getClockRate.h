//
//  getClockRate.h
//  Computer system
//
//  Created by liufapeng on 2020/12/20.
//  Copyright © 2020 liufapeng. All rights reserved.
//

#ifndef getClockRate_h
#define getClockRate_h

#include <stdio.h>

typedef struct {
    uint64_t SYSCLK_Frequency;
    uint64_t HCLK_Frequency;
    uint64_t PCLK1_Frequencey;
    uint64_t PCLK2_Frequencey;
    uint64_t ADCCLK_Frequencey;
} RCC_ClocksTypeDef;

#endif /* getClockRate_h */
