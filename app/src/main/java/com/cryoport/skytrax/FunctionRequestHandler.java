package com.cryoport.skytrax;

import com.cryoport.skytrax.dto.RequestDTO;
import com.cryoport.skytrax.dto.ResponseDTO;
import com.cryoport.skytrax.service.DeviceOnboardingService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.function.aws.MicronautRequestHandler;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionRequestHandler extends MicronautRequestHandler<RequestDTO, ResponseDTO> {
    private static final Logger LOG = LoggerFactory.getLogger(FunctionRequestHandler.class);

    @Inject
    DeviceOnboardingService deviceOnboardingService;

    public FunctionRequestHandler() {
    }

    public FunctionRequestHandler(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Override
    public ResponseDTO execute(RequestDTO input) {
        if(input == null || input.parameters() == null || input.parameters().deviceId() == null) {
            LOG.error("Invalid Input {}",input);
            return null;
        }
        return new ResponseDTO(deviceOnboardingService.checkAndUpdateProvision(input.parameters().deviceId()));
    }
}
