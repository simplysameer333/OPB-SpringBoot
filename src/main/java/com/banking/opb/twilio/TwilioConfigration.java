package com.banking.opb.twilio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
@Data
public class TwilioConfigration {
    private String accountSid;
    private String authToken;
    private String trailNumber;
}
