#!/usr/bin/env bash

# --rm 启动时创建一个新的docker
docker run --name='activemq' -d --rm -p 61616:61616 -p 8161:8161 webcenter/activemq:latest

