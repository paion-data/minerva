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
package com.paiondata.minerva.config;

import com.aliyun.oss.OSS;
import com.paiondata.aliOSS.TestOSSClient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuration for TestClient.
 * <p>
 * Inject a dedicated TestClient for user acceptance testing into the project instead of the real OSSClient.
 */
@Configuration
public class TestClientConfig {

    /**
     * Inject testOSSClient when the athena.acceptance-test.enabled configuration item is true.
     *
     * @return a OSSClient for acceptance test
     */
    @Primary
    @Bean
    @ConditionalOnProperty(name = "athena.acceptance-test.enabled", havingValue = "true")
    public OSS testOSSClient() {
        return new TestOSSClient();
    }
}
