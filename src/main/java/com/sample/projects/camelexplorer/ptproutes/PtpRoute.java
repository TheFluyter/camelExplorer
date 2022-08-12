package com.sample.projects.camelexplorer.ptproutes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PtpRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("from-placeholder")
                .to("to-placeholder");
    }
}
