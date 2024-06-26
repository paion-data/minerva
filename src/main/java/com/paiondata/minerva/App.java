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
package com.paiondata.minerva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot application startup class
 * <p>
 * It is responsible for initializing and running the Spring application context and is the core entry point of
 * the entire Spring Boot application.
 */
@SpringBootApplication
@ComponentScan(value = {"com.paiondata.athena.spring.boot.autoconfigure", "com.paiondata.minerva.config"})
public class App {
    /**
     * Start the SpringBoot application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
