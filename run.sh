#!/usr/bin/env bash

DIR=`pwd`
cd java-federates/TripleIFederation/target
java -jar \
-Djava.library.path=nar/processid-0.3.0-SNAPSHOT-amd64-Linux-gpp-jni/lib/amd64-Linux-gpp/jni/ \
-Djava.net.preferIPv4Stack=true \
TripleIFederation-0.0.1-SNAPSHOT.jar \
TripleIFederation \
TripleSend \
NORMAL
cd $DIR
