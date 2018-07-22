#! /bin/bash

mvn clean package || exit
java -jar target/petdemo*.jar "$@"
