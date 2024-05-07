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

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

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

Since `build` is already included in the docker-compose.yml file, you only need to navigate to Minerva's root directory
and execute one command: `docker compose up`.

When the Minerva application is started successfully, you can upload files to and download the file you need from
Ali Cloud OSS as you like. In addition to this, you can also query the metadata information of the file from database.

File Metadata
--------------

### Inserting file metadata

We can insert a file metadata record by specifying the file's unique identifier fileId, the name of the file and the
file type.

<Tabs>
  <TabItem value="graphql" label="GraphQL">

    ```graphql
    mutation createMetaData {
        createMetaData(fileId: "fileId123", fileName: "pride-and-prejudice.pdf", fileType: "PDF") {
            fileName,
            fileType
        }
    }
    ```

  </TabItem>
</Tabs>

### Querying file metadata

We can get the corresponding file metadata by using fileId.

<Tabs>
  <TabItem value="graphql" label="GraphQL">

    ```graphql
    query {
        metaData(fileId: "fileId123") {
            fileType
            fileName
        }
    }
    ```

  </TabItem>
</Tabs>
