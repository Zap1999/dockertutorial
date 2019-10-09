#!/bin/bash

arguments=( $@ )
workingDirectory=$PWD
senderDirectory="${workingDirectory}/messagesender"
receiverDirectory="${workingDirectory}/messagereceiver"

if [[ " ${arguments[@]} " =~ "-ba" ]]
then
    cd $receiverDirectory
    ./mvnw clean install
    cd $senderDirectory
    ./mvnw clean install
elif [[ " ${arguments[@]} " =~ "-br" ]]
then
    cd $receiverDirectory
    ./mvnw clean install
elif [[ " ${arguments[@]} " =~ "-bs" ]]
then
    cd $senderDirectory
    ./mvnw clean install
fi
docker cp ${receiverDirectory}/target/messagereceiver-0.0.1-SNAPSHOT.jar docker_messagereceiver-container-1_1:/messagereceiver-.0.0.1-SNAPSHOT.jar
docker cp ${senderDirectory}/target/messagesender-0.0.1-SNAPSHOT.jar docker_messagesender-container-1_1:/messagesender-.0.0.1-SNAPSHOT.jar
docker exec -it docker_messagereceiver-container-1_1 ls
docker exec -it docker_messagesender-container-1_1 ls
cd $workingDirectory
