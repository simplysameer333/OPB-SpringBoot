package com.banking.opb.service.impl;

import com.banking.opb.domain.SmsRequest;
import com.banking.opb.service.ISmsSender;
import com.banking.opb.twilio.TwilioConfigration;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender implements ISmsSender {

    private final TwilioConfigration twilioConfigration;

    @Autowired
    public TwilioSmsSender(TwilioConfigration twilioConfigration) {
        this.twilioConfigration = twilioConfigration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfigration.getTrailNumber());
        String message = smsRequest.getMessage();
        Message messageResponse = Message.creator(to, from, message).create();
        messageResponse.getSid();
    }
}
