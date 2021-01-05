#!/bin/env python


defaultValue=0  
maxSoundValue = 300



while (True):
    soundState = input("please input a numer:")
    if (soundState > maxSoundValue):
        defaultValue += 1
        if (defaultValue>>1) == 0:
            print("ledPin, HIGH") 
        elif (defaultValue>>1) == 1:
            print("ledPin, LOW") 
            print(defaultValue)
            defaultValue>>2
            break