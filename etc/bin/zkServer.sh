#!/bin/sh

if [[ $# -eq 0 ]] ; then
    echo 'Please enter 1,2 or 3 as parameter'
    exit 1
fi

if ! [[ "$1" =~ ^[1-3]+$ ]] ; then 
    exec >&2; echo "error: Not a number between 1 and 3"; exit 1
fi

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`/"$link"
    fi
done


PRGDIR=`dirname "$PRG"`
cd "$PRGDIR/.."
PRGDIR_ABS=`pwd `


ZK_VERSION=3.4.8
PATH_TO_ZK=$PRGDIR_ABS/zookeeper-$ZK_VERSION
INSTANCE_DIR=instance-$1

export ZOOCFGDIR=$PRGDIR_ABS/$INSTANCE_DIR/conf
export ZOO_LOG_DIR=$PRGDIR_ABS/$INSTANCE_DIR/logs
cd $PRGDIR_ABS/$INSTANCE_DIR

#remove instance parameter from arguments
shift
#and pass on the rest to the zkServer script
$PATH_TO_ZK/bin/zkServer.sh $@




