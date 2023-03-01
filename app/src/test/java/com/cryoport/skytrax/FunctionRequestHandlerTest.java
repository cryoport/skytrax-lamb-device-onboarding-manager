package com.cryoport.skytrax;

import com.cryoport.skytrax.dto.RequestDTO;
import com.cryoport.skytrax.dto.ResponseDTO;
import com.cryoport.skytrax.entity.DeviceEntity;
import com.cryoport.skytrax.repository.DeviceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.core.io.ResourceResolver;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;


@MicronautTest
class FunctionRequestHandlerTest extends BaseMongoDataTest{

    private static FunctionRequestHandler handler;

    @Inject
    ResourceResolver resourceResolver;
    @Inject
    ObjectMapper mapper;
    @Inject
    DeviceRepository repository;


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
    void testHandler() throws IOException {
        setDevice();
        RequestDTO requestDTO =  mapper.readValue(resourceResolver.getResource("classpath:events/event.json").get(),
                RequestDTO.class);
        ResponseDTO response = handler.execute(requestDTO);
        assertNotNull(response);
        assertTrue(response.allowProvisioning());
    }

    @Test
    void testHandlerNoDevice() throws IOException {
        RequestDTO requestDTO =  mapper.readValue(resourceResolver.getResource("classpath:events/event.json").get(),
                RequestDTO.class);
        ResponseDTO response = handler.execute(requestDTO);
        assertNotNull(response);
        assertFalse(response.allowProvisioning());
    }

    @Test
    void testHandlerInvalidRequest() throws IOException {
        RequestDTO requestDTO =  mapper.readValue(resourceResolver.getResource("classpath:events/event-invalid.json").get(),
                RequestDTO.class);
        ResponseDTO response = handler.execute(requestDTO);
        assertNull(response);
    }


    private void setDevice(){
        DeviceEntity data = new DeviceEntity();
        data.setDeviceId("8627710409503810");
        DeviceEntity saved = repository.save(data);
    }
}
