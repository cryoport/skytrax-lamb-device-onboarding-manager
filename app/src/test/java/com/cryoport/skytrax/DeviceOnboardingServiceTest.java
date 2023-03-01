package com.cryoport.skytrax;

import com.cryoport.skytrax.entity.DeviceEntity;
import com.cryoport.skytrax.repository.DeviceRepository;
import com.cryoport.skytrax.service.DeviceOnboardingService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(transactional = false)
class DeviceOnboardingServiceTest extends BaseMongoDataTest {

    @Inject
    private DeviceOnboardingService deviceOnboardingService;

    @Inject
    DeviceRepository repository;

    private final String DEVICE_ID = "8627710409503810";

    @Test
    void validateCheckAndUpdateProvision(){
        assertDoesNotThrow(() -> setDevice());
        assertTrue(deviceOnboardingService.checkAndUpdateProvision(DEVICE_ID));
    }

    @Test
    void validateCheckAndUpdateProvisionNoDevice(){
        assertFalse(deviceOnboardingService.checkAndUpdateProvision(DEVICE_ID));
    }

    @Test
    void validateCheckAndUpdateProvisionWithProvisionTrue(){
        assertDoesNotThrow(() -> setDeviceWithProvison(Boolean.TRUE));
        assertTrue(deviceOnboardingService.checkAndUpdateProvision(DEVICE_ID));
    }

    @Test
    void validateCheckAndUpdateProvisionWithProvisionFalse(){
        assertDoesNotThrow(() -> setDeviceWithProvison(Boolean.FALSE));
        assertFalse(deviceOnboardingService.checkAndUpdateProvision(DEVICE_ID));
    }

    private void setDevice(){
        DeviceEntity data = new DeviceEntity();
        data.setDeviceId(DEVICE_ID);
        DeviceEntity saved = repository.save(data);
    }

    private void setDeviceWithProvison(Boolean provision){
        DeviceEntity data = new DeviceEntity();
        data.setDeviceId(DEVICE_ID);
        data.setAllowProvisioning(provision);
        DeviceEntity saved = repository.save(data);
    }
}
