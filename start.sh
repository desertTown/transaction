#!/usr/bin/env bash

docker run --name mysql1 -e MYSQL_ROOT_PASSWORD=123456 \
        -e MYSQL_DATABASE=db1 -e MYSQL_ROOT_HOST=% \
        -e MYSQL_USER=nick -e MYSQL_PASSWORD=123456  -d -p 3307:3306 \
        -v $PWD/mysql_data:/var/lib/mysql mysql/mysql-server:5.7