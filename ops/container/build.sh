#! /bin/bash
cp ../../mvc/target/*.war ./
docker build -t car-api -f Dockerfile .
rm *.war

# start container
# docker run --name test-api car-api
