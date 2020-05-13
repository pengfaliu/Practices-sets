#!/usr/local/bin/lua

tab1 = { key1 = "val1", key2 = "val2", "val3" }
for k, v in pairs(tab1)
    do
    print(k .. " - " .. v)
end



tab2 = {a=1,b=2,c=3}

for k,v in pairs(tab2)
    do
    print(k .. '<--->' .. v)
end


tab2.a='liufapeng'

for k,v in pairs(tab2)
    do
    print(k .. '<-->' ..v)
end


print(tab2.a)
print(tab2.b)
print(tab2.c)
