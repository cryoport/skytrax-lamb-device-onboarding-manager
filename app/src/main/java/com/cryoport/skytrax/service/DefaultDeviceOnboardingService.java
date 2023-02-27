package com.cryoport.skytrax.service;

import com.cryoport.skytrax.entity.DeviceEntity;
import com.cryoport.skytrax.repository.DeviceRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class DefaultDeviceOnboardingService implements DeviceOnboardingService{

    @Inject
    private DeviceRepository deviceRepository;

    @Override
    public boolean checkAndUpdateProvision(String deviceId) {
        Optional<DeviceEntity> device =  deviceRepository.findById(deviceId);
        if( device.isPresent() && (device.get().getAllowProvisioning() == null || device.get().getAllowProvisioning())) {
            device.get().setAllowProvisioning(Boolean.FALSE);
            deviceRepository.update(device.get());
            return Boolean.TRUE;
        }
        return false;
    }
}
