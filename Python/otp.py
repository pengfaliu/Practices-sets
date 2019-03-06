import pyotp
import base64,time

for i in range(1,10):
   secretkey= base64.b32encode("hellodldlkdklslkldsjljfsljfd slfsjfljflslfjalsjfsljf")
   totp = pyotp.TOTP(secretkey)
   s=totp.now()
   print s
   time.sleep(15)
