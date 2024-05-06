/*
 * Copyright 2024 Paion Data
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.paiondata.minerva

import com.paiondata.athena.file.File
import com.paiondata.athena.file.identifier.FileIdGenerator
import com.paiondata.athena.filestore.FileStore
import com.paiondata.athena.filestore.swift.SwiftFileStore
import com.paiondata.athena.metadata.FileType
import com.paiondata.athena.metadata.MetaData

import org.javaswift.joss.client.factory.AccountFactory
import org.javaswift.joss.client.factory.AuthenticationMethod
import org.javaswift.joss.model.Account
import org.javaswift.joss.model.Container
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.spock.Testcontainers

import jakarta.validation.constraints.NotNull
import spock.lang.Specification
import spock.lang.Subject

import java.nio.charset.StandardCharsets

@Testcontainers
class DockerComposeITSpec extends Specification{
    static final int WS_PORT = 8080
    static final String FILE_ID = "fileId"
    static final String FILENAME = "pride-and-prejudice-by-jane-austen.txt"

    final DockerComposeContainer COMPOSE = new DockerComposeContainer(new java.io.File("docker-compose.yml"))
            .withExposedService(
                    "web",
                    WS_PORT
            )

    @Subject
    FileStore fileStore

    def setup() {
        COMPOSE.start()

        Account account = buildAccount()

        final Container container = account.getContainer(SwiftFileStore.DEFAULT_CONTAINER)

        if (!container.exists()) {
            container.create()
            container.makePublic()
        }

        fileStore = new SwiftFileStore(account, buildFileIdGenerator())
    }

    def cleanup() {
        COMPOSE.stop()
    }

    @SuppressWarnings("GroovyAccessibility")
    def "File can be uploaded and downloaded in its intact form"() {
        when: "a file is uploaded"
        fileStore.upload(
                new File(
                        new MetaData(FILENAME, FileType.TXT),
                        getClass().getClassLoader().getResourceAsStream(FILENAME)
                )
        )

        then: "the file can be downloaded later"
        fileStore.download(FILE_ID).getText(StandardCharsets.UTF_8.name()) ==
                getClass().getClassLoader().getResourceAsStream(FILENAME).getText(StandardCharsets.UTF_8.name())
    }

    @NotNull
    Account buildAccount() {
        new AccountFactory()
                .setUsername("chris:chris1234")
                .setPassword("testing")
                .setAuthUrl("http://127.0.0.1:12345/auth/v1.0")
                .setAuthenticationMethod(AuthenticationMethod.BASIC)
                .setMock(true)
                .createAccount()
    }

    @NotNull
    FileIdGenerator buildFileIdGenerator() {
        new FileIdGenerator() {
            @Override
            String apply(final File file) {
                return FILE_ID
            }
        }
    }
}
