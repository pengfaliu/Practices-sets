#ifndef SR04_H
#define SR04_H

#if defined(ARDUINO) && ARDUINO >=100
    #include "Arduino.h"
#else
    #include "WProgram.h"
#endif 

#include "SR04.h"

SR04::SR04(int pin) {
    _pin = pin;
}
/*
SR04::SR04(int tp ,int ep) {
    pinMode(tp,OUTPUT);
    pinMode(ep,INPUT);
    Trig_pin = tp;
    Echo_pin = ep;
}
*/

long SR04::Get() {

   /* //发送超声波 The measured distance from the range 0 to 400 Centimeters
    digitalWrite(Trig_pin,LOW);
    delayMicroseconds(2);
    digitalWrite(Trig_pin,HIGH);
    delayMicroseconds(5);
    digitalWrite(Trig_pin,LOW);

    //接收超声波并计算距离
    long distance = pulseIn(Echo_pin,HIGH)/58.00;
    return distance;

    */

	pinMode(_pin, OUTPUT);
	digitalWrite(_pin, LOW);
	delayMicroseconds(2);
	digitalWrite(_pin, HIGH);
	delayMicroseconds(5);
	digitalWrite(_pin,LOW);
	pinMode(_pin,INPUT);
	long duration;
	duration = pulseIn(_pin,HIGH);
	long RangeInCentimeters;
	RangeInCentimeters = duration/29/2;
	return RangeInCentimeters;
   
}
#endif