package com.immotef.corona.trace;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class TracingControllerTest {


    @Autowired
    private TracingService userService;

    @Test
    void traceMe() {
        StatusResponse statusResponse = userService.statusForIdentifier(3);
        assertTrue(statusResponse.items.size() > 0);
    }
}