#!/bin/bash
sed 's/tageName/$1/g' /home/s0pheap/jenkins/deployment.yaml

#delete image to get the lastest image from dockerhub
images=`docker images | grep s0pheap/demo-jenkins | awk '{print $3}'`
docker rmi -f $images

#apply configuration to K8s
kubectl apply -f /home/s0pheap/jenkins/deployment.yaml