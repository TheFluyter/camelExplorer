package com.sample.projects.camelexplorer.routes;

import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.HTTP_METHOD;
import static org.apache.camel.Exchange.HTTP_URI;

@Component
@PropertySource(value = "classpath:camelexplorer.properties")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:camelexplorer-${spring.profiles.active}.properties")
public class RestRouteCollector extends RouteBuilder {

    public static final String ROUTE_ID_BASE = "camel-explorer.rest.route.collector";
    public static final String ROUTE_ID_COLLECTOR_START = ROUTE_ID_BASE + ".start";

    private final JacksonDataFormat magicCardDataFormat;

    @PropertyInject(value = "camel-explorer.rest.route.collector.from")
    private String restRouteCollectorFrom;

    @PropertyInject(value = "camel-explorer.rest.route.collector.to")
    private String restRouteCollectorTo;

    @PropertyInject(value = "camel-explorer.rest.route.url")
    private String restRouteUrl;

    @PropertyInject(value = "camel-explorer.rest.route.collector.autostartup", defaultValue = "true")
    private String autostartup;

    public RestRouteCollector(JacksonDataFormat magicCardDataFormat) {
        this.magicCardDataFormat = magicCardDataFormat;
    }

    @Override
    public void configure() {
        from(restRouteCollectorFrom).routeId(ROUTE_ID_COLLECTOR_START).autoStartup(autostartup)
                .setHeader(HTTP_METHOD, constant("GET"))
                .setHeader(HTTP_URI, constant(restRouteUrl))
                .log("Attempting to get a random card from Scryfall")
                .to("http:placeholder/see-http-uri")
                .log("Received status code ${header.CamelHttpResponseCode} with payload ${body}")
                .unmarshal(magicCardDataFormat)
                .log("BODY: ${body}");
    }
}
