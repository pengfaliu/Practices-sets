#ifndef SR04_H
#define SR04_H

#if defined(ARDUINO) && ARDUINO >=100
    #include "Arduino.h"
#else
    #include "WProgram.h"
#endif 

#include "SR04.h"

SR04::SR04(int tp ,int ep) {
    pinMode(tp,OUTPUT);
    pinMode(ep,INPUT);
    Trig_pin = tp;
    Echo_pin = ep;
}

float SR04::Get() {

    //发送超声波
    digitalWrite(Trig_pin,LOW);
    delayMicroseconds(2);
    digitalWrite(Trig_pin,HIGH);
    delayMicroseconds(100);
    digitalWrite(Trig_pin,LOW);

    //接收超声波并计算距离
    float distance = pulseIn(Echo_pin,HIGH)/58.00 ;
    return distance;
}
#endif