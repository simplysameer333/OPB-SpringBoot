package com.banking.opb.twilio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("twilio")
@Data
public class TwilioConfigration {
    private String accountSid;
    private String authToken;
    private String trailNumber;
}
