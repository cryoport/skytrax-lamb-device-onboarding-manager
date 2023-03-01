package com.cryoport.skytrax.service;

public interface DeviceOnboardingService {

    /**
     * Validates whether device is provisioned to use.
     * @return
     */
    boolean checkAndUpdateProvision(String deviceId);
}
