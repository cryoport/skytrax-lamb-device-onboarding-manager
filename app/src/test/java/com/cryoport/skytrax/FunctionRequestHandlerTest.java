package com.cryoport.skytrax;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@MicronautTest
class FunctionRequestHandlerTest extends BaseMongoDataTest{

    private static FunctionRequestHandler handler;

    @BeforeAll
    void setupServer() {
        handler = new FunctionRequestHandler(application.getApplicationContext());
    }

    @AfterAll
    void stopServer() {
        if (handler != null) {
            handler.getApplicationContext().close();
        }
    }

    @Test
    void testHandler() {
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setHttpMethod("GET");
        request.setPath("/");
        APIGatewayProxyResponseEvent response = handler.execute(request);
        assertEquals(200, response.getStatusCode().intValue());
        assertEquals("{\"message\":\"Hello World\"}", response.getBody());
    }
}
