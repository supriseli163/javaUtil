#!/usr/bin/env bash
i=0
for project in $(ls)
do
    echo ${project}
    i=`expr $i + 1`
    echo "now compress number is: ${i}"
    destination="/Users/supriseli/IdeaProjects/basis-study/javaUtil/java-util/src/test/java/com/util/java/util/basic/compress/"${project}
    echo ${destination}
    tar -zcvf ${destination}.tar.gz ${project} > /dev/null 2>&1
done
