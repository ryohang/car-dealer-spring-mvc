#! /bin/bash

docker build -t car-api -f Dockerfile .

# start container
# docker run --name test-api car-api
