/*
test
*/
/*#ifndef SR04_H
#define SR04_H
*/

#if defined(ARDUINO) && ARDUINO >=100
    # include "Arduino.h"
#else
    # include "WProgram.h"
#endif 

class SR04
{
private:
    int Trig_pin;
    int Echo_pin;
    long distance;
    int _pin;
public:
    SR04(int pin);
    //SR04(int TrigPin,int EchoPin);
    //~SR04();

    long Get();
};

//#endif 