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
                .log("PTP route \"" + ROUTE_ID + "\" picked up a file from " + PTP_ROUTE_FROM)
                .to(PTP_ROUTE_TO)
                .log("PTP route \"" + ROUTE_ID + "\" stored a file in " + PTP_ROUTE_FROM);
    }
}