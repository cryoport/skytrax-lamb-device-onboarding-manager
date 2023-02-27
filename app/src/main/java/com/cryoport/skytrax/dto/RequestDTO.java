package com.cryoport.skytrax.dto;


import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record RequestDTO(
        String claimCertificateId,
        String certificateId,
        String certificatePem,
        String templateArn,
        String clientId,
        Parameters parameters
) {

}
