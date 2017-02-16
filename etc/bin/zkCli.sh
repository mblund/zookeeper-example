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
cd "$PRGDIR/.."
PRGDIR_ABS=`pwd`

ZK_VERSION=3.4.8
PATH_TO_ZK=$PRGDIR_ABS/zookeeper-$ZK_VERSION

$PATH_TO_ZK/bin/zkCli.sh  -server 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183

