package com.cryoport.skytrax.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Parameters(
        @JsonProperty("DeviceId")
        String deviceId
) {
}
