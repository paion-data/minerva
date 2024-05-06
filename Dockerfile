# Copyright 2024 Paion Data
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

FROM ubuntu:22.04

LABEL maintainer="A-Little-Excited"
LABEL maintainer-email="3030036858@qq.com"

ARG WS_VERSION=1.0-SNAPSHOT

ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64

RUN apt update
RUN apt upgrade -y
RUN apt install software-properties-common -y
RUN apt install wget

# Install JDK 17 - https://www.rosehosting.com/blog/how-to-install-java-17-lts-on-ubuntu-20-04/
RUN apt update -y
RUN apt install openjdk-17-jdk -y

COPY ./target/minerva-$WS_VERSION.jar /app/minerva.jar

CMD ["java","-jar","/app/minerva.jar"]
