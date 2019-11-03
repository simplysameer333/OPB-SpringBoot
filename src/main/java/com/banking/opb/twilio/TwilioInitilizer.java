package com.banking.opb.twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class TwilioInitilizer {
    private final TwilioConfigration twilioConfigration;

    @Autowired
    public TwilioInitilizer(TwilioConfigration config) {
        this.twilioConfigration = config;
        Twilio.init(twilioConfigration.getAccountSid(), twilioConfigration.getAuthToken());
        log.info("Twilio Initilized");
    }
}
