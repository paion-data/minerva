---
title: Getting Started
sidebar_position: 1
---

[//]: # (Copyright 2024 Paion Data)

[//]: # (Licensed under the Apache License, Version 2.0 &#40;the "License"&#41;;)
[//]: # (you may not use this file except in compliance with the License.)
[//]: # (You may obtain a copy of the License at)

[//]: # (    http://www.apache.org/licenses/LICENSE-2.0)

[//]: # (Unless required by applicable law or agreed to in writing, software)
[//]: # (distributed under the License is distributed on an "AS IS" BASIS,)
[//]: # (WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.)
[//]: # (See the License for the specific language governing permissions and)
[//]: # (limitations under the License.)

Getting Started
===============

Minerva is designed to be lightweight and easy to use. It is a web service for object storage services. You can use it
to store objects in Ali Cloud OSS. You just need to pass your Ali Cloud access credentials to use this service.

:::caution

Before you start using Minerva, we have to tell you something: We only provides Spring/SpringBoot paradigm, and it is
deployed and run as a JAR. So please check whether it meets your needs before using it.

:::

Docker Compose
--------------

With docker compose, you can quickly set up a complete Minerva application by starting two services: web service and
database service. 

Since `build` is already included in the docker-compose.yml file, you only need to navigate to Minerva's root directory and
execute one command: `docker compose up`.

Inside [docker-compose.yml][docker-compose.yml] :
```yaml
services:
  web:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
      - SPRING_DATASOURCE_URL=jdbc:mysql://db/athena?serverTimezone=UTC
      - SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.jdbc.Driver
      - SPRING_JDBC_DATABASE_PLATFORM=org.hibernate.dialect.MySQLDialect
      - HIBERNATE_HBM2DDL_AUTO=create
      - ATHENA_SPRING_ALIOSS_ENABLED=true
      - ATHENA_TEST_ENABLED=true
    depends_on:
      db:
        condition: service_healthy
  db:
    image: "mysql:5.7.43"
    ports:
      - "3306:3306"
    volumes:
      - "${MYSQL_INIT_SCRIPT_PATH:-./mysql-init.sql}:/docker-entrypoint-initdb.d/mysql-init.sql"
    environment:
      MYSQL_ROOT_PASSWORD: root
    command: --character-set-server=utf8 --collation-server=utf8_general_ci
    healthcheck:
      test: mysqladmin ping -h localhost -u root -proot
      timeout: 3s
      retries: 3
```

When the Minerva application is started successfully, you can upload files to and download the file you need from Ali Cloud OSS as you like.

[docker-compose.yml]:https://github.com/paion-data/minerva/blob/master/docker-compose.yml