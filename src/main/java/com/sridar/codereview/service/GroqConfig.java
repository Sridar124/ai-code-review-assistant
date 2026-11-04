
package com.sridar.codereview.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GroqConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
