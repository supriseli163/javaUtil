#!/bin/sh
#
# A system monitoring tool
#
# which can generate results as a table
#
#   yshen 2016
#


#global configuration
INTERVAL=1
OUTFILE="monitor.log"

#monitoring configuration
MONITOR_LOAD=1
MONITOR_MEM=1
MONITOR_IO=1
MONITOR_PROCESS=1
PRO_NAME="Xorg"

#internal variables
time=""
result=""
loops=0

#monitoring functions
monitor_load() {
    load_avg=$(uptime | awk '{print $9}' | sed 's/,$//g')
    col="$col|-load-"
    result="$result$load_avg\t"
}

monitor_mem() {
    mem_used=$(free -m|sed -n "2, 1p"  | awk '{print $3}')
    mem_free=$(free -m|sed -n "2, 1p"  | awk '{print $4}')
    mem_cache=$(free -m|sed -n "2, 1p"  | awk '{print $6}')
    mem_avali=$(free -m|sed -n "2, 1p"  | awk '{print $7}')
    mem_dirty_kb=$(cat /proc/meminfo  | grep Dirty | awk '{print $2}') #in kb
    mem_dirty=$((mem_dirty_kb/1024)) #in mb
    #col="$col|-m_used-|-m_free-|--m_cache--|-m_aval-|"
    col="$col|-m_used-|-m_aval-|-m_ditry-"
    result="$result$mem_used\t$mem_avali\t$mem_dirty\t"
}

monitor_io() {
    dev_count=$(iostat -xmd | wc -l)
    dev_count=$((dev_count - 4))
    for i in $(seq $dev_count); do
        line=$((i+3))
        dev_util=$(iostat -xmd | sed -n "$line,1p"| awk '{print $14}')
        dev_name=$(iostat -xmd | sed -n "$line,1p"| awk '{print $1}')
        col="$col|-$dev_name--"
        result="$result$dev_util\t"
    done
}

monitor_process() {
    process_id=$( ps aux | grep $PRO_NAME | sed -n "1,1p" | awk '{print $2}')
    process_cpu=$(top -b -p 3303 -n1 | sed -n "8,1p" | awk '{print $9}')
    col="$col|-%CPU-"
    result="$result$process_cpu\t"
}

#main entry point

#the first argument is process name to monitor
if [ $# -eq 0 ] ; then
    #no arg, turn off monitor process
    MONITOR_PROCESS=0
fi

if [ $# -eq 1 ] ; then
    process_exist=$(ps aux | grep $PRO_NAME | wc -l)
    if [ $process_exist -eq 1 ] ; then
        MONITOR_PROCESS=0
    fi
    if [ $process_exist -eq 2 ] ; then
        PRO_NAME=$1
    fi
fi

while [ 1 -eq 1 ] ; do
    result=""
    col=""

    # sleep until next integer seconds
    sleep=$(date '+%s.%N' | awk "{print $INTERVAL - (\$1 % $INTERVAL)}")
    sleep $sleep

    time=$(date '+%F_%k:%M:%S.%N')
    #time=$(date '+%F_%k:%M:%S')
    result="$result$time\t"
    #        2016-10-11_17:45:34.002592500
    col="$col------------time-------------"
    # do the work

    ## COL1 monitoring the load average
    if [ $MONITOR_LOAD -eq 1 ]
    then
        monitor_load
    fi

    ## COL1 monitoring the load average
    if [ $MONITOR_MEM -eq 1 ]
    then
        monitor_mem
    fi

    ## COL1 monitoring the load average
    if [ $MONITOR_IO -eq 1 ]
    then
        monitor_io
    fi
    ## COL1 monitoring the load average
    if [ $MONITOR_PROCESS -eq 1 ]
    then
        monitor_process
    fi
    ## extra COLs add below

    # do the print
    ## on the first iteration print the col names
    if [ $loops -eq 0 ]
    then
        echo $col >> $OUTFILE
        echo $col
    fi

    echo $result >> $OUTFILE
    echo $result

    loops=$((1))
done