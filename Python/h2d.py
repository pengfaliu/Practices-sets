#!/usr/bin/python
# Convert list of hex into decimal

import sys

for i in xrange(1,len(sys.argv)):
    print("%s = %d" % (sys.argv[i],int(sys.argv[i],16)))
