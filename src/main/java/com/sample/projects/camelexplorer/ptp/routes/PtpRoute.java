package com.sample.projects.camelexplorer.ptp.routes;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:camelexplorer.properties")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:camelexplorer-${spring.profiles.active}.properties")
public class PtpRoute extends RouteBuilder {

    public static final String ROUTE_ID_BASE = "camel-explorer.ptp.collector";
    public static final String ROUTE_ID_COLLECTOR_START = ROUTE_ID_BASE + ".start";

    @PropertyInject(value = "camel-explorer.ptp.collector.from")
    private String ptpCollectorFrom;

    @PropertyInject(value = "camel-explorer.ptp.collector.to")
    private String ptpCollectorTo;

    @PropertyInject(value = "camel-explorer.ptp.collector.autostartup", defaultValue = "true")
    private String autostartup;

    @Override
    public void configure() {
        from(ptpCollectorFrom).routeId(ROUTE_ID_COLLECTOR_START).autoStartup(autostartup)
                .log("Route started")
                .to(ptpCollectorTo);
    }
}