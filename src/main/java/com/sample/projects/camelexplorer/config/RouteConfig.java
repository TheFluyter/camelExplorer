package com.sample.projects.camelexplorer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.projects.camelexplorer.domain.MagicCard;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    ObjectMapper mapper = new ObjectMapper();

    @Bean
    public JacksonDataFormat magicCardDataFormat() {
        JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(MagicCard.class);
        jacksonDataFormat.setObjectMapper(mapper);
        return jacksonDataFormat;
    }
}
