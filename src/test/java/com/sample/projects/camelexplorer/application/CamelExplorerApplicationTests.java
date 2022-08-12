package com.sample.projects.camelexplorer.application;

import com.sample.projects.camelexplorer.CamelExplorerApplication;
import org.apache.camel.CamelContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CamelExplorerApplication.class)
class CamelExplorerApplicationTests {

    @Autowired
    private CamelContext camelContext;

    @Test
    void contextLoads() {
        assertEquals(1, camelContext.getRoutes().size());
    }
}
