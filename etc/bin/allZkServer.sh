#!/bin/sh

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

$PRGDIR/zkServer.sh 1 $@
$PRGDIR/zkServer.sh 2 $@
$PRGDIR/zkServer.sh 3 $@