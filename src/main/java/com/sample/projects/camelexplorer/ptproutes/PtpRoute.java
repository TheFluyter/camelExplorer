package com.sample.projects.camelexplorer.ptproutes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PtpRoute extends RouteBuilder {

    public static final String ROUTE_ID = "ptp.plain.route";
    public static final String PTP_ROUTE_FROM = "file://mnt/tst/files/ptp/plain/source";
    public static final String PTP_ROUTE_TO = "file://mnt/tst/files/ptp/plain/destination";

    @Override
    public void configure() {
        from(PTP_ROUTE_FROM + "?delete=true")
                .routeId(ROUTE_ID)
                .log("Plain PTP route started")
                .to(PTP_ROUTE_TO);
    }
}