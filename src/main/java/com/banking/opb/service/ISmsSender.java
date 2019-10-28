package com.banking.opb.service;

import com.banking.opb.domain.custom.SmsRequest;

public interface ISmsSender {
    void sendSms(SmsRequest smsRequest);
}
