-- table_test3.lua 脚本文件
a3 = {}
for i = 1, 10 do
    a3[i] = i
    print('a3[i]'.. '=' .. a3[i])
end

a3["key"] = "val"
print(a3["key"])
print(a3["none"])


for k,v in pairs(a3)
    do
    print(k .. '=' .. v)
end
