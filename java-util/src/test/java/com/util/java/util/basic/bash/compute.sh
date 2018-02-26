#!/usr/bin/env bash
a=10
b=20

val=`expr $a + $b`
echo "a + b : $val"

val=`expr $a - $b`
echo "a - b : $val"

val=`expr $a \* $b`
echo "a * b : $val"

val=`expr $b / $a`
echo "b / a : $val"

if [$a == $b]
then
    echo "a is equal to b"
fi

if [$a != $b]
then
    echo "a is not equal to b"
fi

# Linux shell需要注意的是乘号(*)前必须加反斜杠才能实现乘法运算

# Linux文件测试运算符
# 文件测试运算符用于检测Unix文件的各种属性
# 例如:变量file表示文件“/var/www/tutorialspoint/unix/test.sh”,它的大小为100字节,具有rwx权限,下面的代码,将检测该文件的各种属性

