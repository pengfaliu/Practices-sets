#!/usr/bin/env python
#coding:utf8
# ocr test
import pytesseract
from PIL import Image

# open image
image = Image.open('/Users/lfp/Downloads/influxdbtarget.png')
code = pytesseract.image_to_string(image, lang='chi_sim')
print(code)
