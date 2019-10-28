package com.banking.opb.service.impl;

import com.banking.opb.Utilities.BasicUtilities;
import com.banking.opb.domain.custom.Card;
import com.banking.opb.domain.custom.SmsRequest;
import com.banking.opb.service.ICardService;
import com.banking.opb.service.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardServiceImpl implements ICardService {

    private static Map<String, Card> cardCache = new HashMap<>();

    @Autowired
    private TwilioSmsSender twilioSmsSender;

    @Override
    public String addCard(Card cardInfo) {
        if (BasicUtilities.isEmptyOrNullString(cardInfo.getAccountId())
                || BasicUtilities.isEmptyOrNullString(cardInfo.getCardNumber())
                || BasicUtilities.isEmptyOrNullString(cardInfo.getBranchId()))
            return "MandatoryMissing";
        if (cardCache.containsKey(cardInfo.getCardNumber()))
            return "CardExists";
        cardInfo.setId("user_".concat(String.valueOf(cardCache.size() + 1)));
        cardCache.put(cardInfo.getCardNumber(), cardInfo);
        return cardInfo.getCardNumber();
    }

    @Override
    public boolean setDefault(String card_id) {
        cardCache.get(card_id.trim()).setDefault(true);
        return true;
    }

    @Override
    public Card getCardInfo(String card_id) {
        return cardCache.get(card_id.trim());
    }

    @Override
    public void generateCode(String card_id) {
        int passCode = BasicUtilities.getRandomNumberInRange();
        SmsRequest smsRequest = new SmsRequest(cardCache.get(card_id.trim()).getPhone(),
                "Your passCode is " + passCode);
        twilioSmsSender.sendSms(smsRequest);
        cardCache.get(card_id.trim()).setLastCode(passCode);
    }

    @Override
    public boolean validateCode(String card_id, int passCode) {
        return passCode == cardCache.get(card_id.trim()).getLastCode();
    }
}
