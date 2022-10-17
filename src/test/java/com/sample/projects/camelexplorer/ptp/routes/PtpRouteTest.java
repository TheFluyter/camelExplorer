package com.sample.projects.camelexplorer.ptp.routes;

import org.apache.camel.*;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import static com.sample.projects.camelexplorer.ptp.routes.PtpRoute.*;
import static org.apache.camel.builder.AdviceWith.adviceWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CamelSpringBootTest
@EnableAutoConfiguration
@PropertySource(value = "classpath:tst.properties")
@SpringBootTest(classes = PtpRoute.class)
@UseAdviceWith
class PtpRouteTest {

    @Autowired
    CamelContext context;

    @EndpointInject("direct:mock.route.plain.from")
    Endpoint mockPlainRouteFrom;

    @EndpointInject("mock:mock.route.to")
    MockEndpoint mockPlainRouteTo;

    @Produce
    ProducerTemplate producerTemplate;

    @PropertyInject(value = "camel-explorer.ptp.collector.to")
    private String ptpCollectorTo;

    @BeforeEach
    void prepare() throws Exception {
        adviceWith(context, ROUTE_ID_COLLECTOR_START, a -> {
            a.replaceFromWith(mockPlainRouteFrom);
            a.weaveByToUri(ptpCollectorTo).replace().to(mockPlainRouteTo);
        });

        context.start();
    }

    @AfterEach
    void cleanUp() {
        context.stop();
    }

    @Test
    void testHappyFlow() {
        String input = "A good day to you, sir!";

        producerTemplate.sendBody(mockPlainRouteFrom, input);

        String actual = mockPlainRouteTo.getReceivedExchanges().get(0).getIn().getBody(String.class);
        assertEquals(actual, input);
    }
}
