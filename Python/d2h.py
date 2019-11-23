#!/usr/bin/python
# Convert list of decimal numbers into hex

import sys

for i in xrange(1,len(sys.argv)):
    print("%d = 0x%x" % (int(sys.argv[i]),int(sys.argv[i])))
