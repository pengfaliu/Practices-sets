-- 创建一个空的 table
local tbl1 = {}
 
-- 直接初始表
local tbl2 = {"apple", "pear", "orange", "grape"}

print(tbl1)
print(tbl2)


for key, val in pairs(tbl2) do
    print("Key", key)
end
