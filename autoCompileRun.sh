#!/bin/bash

jrun()
{
file=$1
echo ${file} | grep -q "\."
if [ $? -eq 0 ];then
suffix=${file##*.}
if [ ${suffix} = 'java' ];then
echo "------------------------ begin compile ${file} --------------------------------------"
javac -d ./bin ${file}
flag=$?
echo "----------------------------------- end --------------------------------------"

echo "######################### run compile ${file%.*}.class ###################################"
[[ ${flag} -eq 0 ]] && java -classpath ./bin/ ${file%.*}  || echo "compile error"
echo ""
echo "#################################### end ###############################################"
fi
fi
}
