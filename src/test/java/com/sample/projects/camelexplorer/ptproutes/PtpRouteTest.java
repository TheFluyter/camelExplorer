package com.sample.projects.camelexplorer.ptproutes;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static com.sample.projects.camelexplorer.ptproutes.PtpRoute.PTP_ROUTE_TO;
import static com.sample.projects.camelexplorer.ptproutes.PtpRoute.ROUTE_ID;
import static org.apache.camel.builder.AdviceWith.adviceWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(classes = PtpRoute.class)
class PtpRouteTest {

    @Autowired
    CamelContext context;

    @EndpointInject("direct:mock.route.from")
    Endpoint mockPtpRouteFrom;

    @EndpointInject("mock:mock.route.to")
    MockEndpoint mockPtpRouteTo;

    @Produce
    ProducerTemplate producerTemplate;

    @BeforeEach
    void prepare() throws Exception {
        adviceWith(context, ROUTE_ID, a -> {
            a.replaceFromWith(mockPtpRouteFrom);
            a.weaveByToUri(PTP_ROUTE_TO).replace().to(mockPtpRouteTo);
        });
    }

    @Test
    void testHappyFlow() {
        String expected = "A good day to you, sir!";

        producerTemplate.sendBody(mockPtpRouteFrom, "A good day to you, sir!");

        String actual = mockPtpRouteTo.getReceivedExchanges().get(0).getIn().getBody(String.class);
        assertEquals(actual, expected);
    }
}