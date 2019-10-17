package com.banking.opb.twilio;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitilizer {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());
    private final TwilioConfigration twilioConfigration;

    @Autowired
    public TwilioInitilizer(TwilioConfigration config) {
        this.twilioConfigration = config;
        Twilio.init(twilioConfigration.getAccountSid(), twilioConfigration.getAuthToken());
        LOGGER.info("Twilio Initilized");
    }
}
