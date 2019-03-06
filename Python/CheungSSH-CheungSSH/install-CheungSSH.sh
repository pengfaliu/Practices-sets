#!/bin/bash
#Author=Cheung Kei-Chuen
#QQ=2418731289
#如果您在使用过程中，遇到了一点点的问题，我都真诚希望您能告诉我！为了改善这个软件， 方便您的工作#
export LANG=zh_CN.UTF-8
if [ `id -u` -ne 0 ]
then
	echo "Must be as root install!"
	exit 1
fi

echo  "Installing..."
rpm  -qa|grep gcc -q
if  [ $? -ne 0 ]
then
	echo  "您的系统当前没有gcc环境！"
	echo "现在为您安装GCC..."
	yum  install -y gcc >/dev/null 2>/dev/null
	if [ $? -ne 0 ]
	then
		echo  "安装GCC失败了，请手动安装gcc。"
		exit 1
	else
		echo "GCC安装成功!"
	fi

fi
rpm  -qa|grep python-devel -q
if  [ $? -ne 0 ]
then
	echo  "您的系统没有python-devle包，现在需要安装..."
	yum  install -y python-devel  2>/dev/null
	if  [ $? -ne  0 ]
	then
		echo "无法安装python-devel，请手动安装python-devel"
		exit 1
	else
		echo "安装python-devel成功！"
	fi
fi

which easy_install 2>/dev/null >/dev/null
if [ $? -ne 0 ]
then
        echo "正在下载 easy_install"
        wget -q http://peak.telecommunity.com/dist/ez_setup.py
        if [ $? -ne 0 ]
        then
                echo "您的系统没有 easy_install 命令，请手动安装这个命令！"
                exit 1
        else
                python ez_setup.py
                if [ $? -ne 0 ]
                then
                        echo "安装easy_install不成功，请尝试重新安装，或者手动安装 如果还是不成功，请使用第二种方式手动安装: http://blog.chinaunix.net/uid-29295703-id-4663051.html"
			exit

                else
                        echo "安装easy_install成功"
                fi
        fi
fi


which pip 2>/dev/null
if [ $? -ne 0 ]
then
	easy_install pip
	if [ $? -ne 0 ]
	then
		echo "安装pip失败，请手动安装pip"
		exit 1
	else
		echo "安装pip成功"
	fi
fi

pip install  pycrypto
if [ $? -ne 0 ]
then
	echo "安装pycrypto失败，请手动下载安装:https://pypi.python.org/packages/source/p/pycrypto/pycrypto-2.6.1.tar.gz"
	exit 1
else
	echo "安装pycrypto成功"
fi

pip install  paramiko
if [ $? -ne 0 ]
then
	echo "安装paramiko失败，请手动下载后安装 https://pypi.python.org/packages/source/p/paramiko/paramiko-1.9.0.tar.gz"
	exit 1
else
	echo "安装paramiko成功"
fi
#########
chmod -R a+x CheungSSH*  2>/dev/null
chmod a+x *.py 2>/dev/null
cat <<EOF|python
#encoding:utf-8
try:
 import paramiko
except AttributeError:
 import os
 os.system("""sed  -i '/You should rebuild using libgmp/d;/HAVE_DECL_MPZ_POWM_SEC/d'  /usr/lib64/python*/site-packages/Crypto/Util/number.py       /usr/lib/python*/site-packages/pycrypto*/Crypto/Util/number.py""") #有的系统可能需要修改一下这个语句，否则会报错
EOF
cp *py /cheung/bin/ 2>/dev/null
echo 'PATH=$PATH:~/cheung/bin' >>/etc/profile
. /etc/profile
cat <<EOFF|python
#coding:utf8
import sys
try:
	import hashlib
except:
	print "请手动安装hashlib"
try:
	import paramiko,readline,filemd5
	print "模块正常"
except Exception,e:
	print "模块尚未安装，无法使用: ",e
	sys.exit(1)
EOFF
echo "恭喜，您已经安装好了环境，接下来请您使用 ./`ls cheungssh*` 启动程序"
