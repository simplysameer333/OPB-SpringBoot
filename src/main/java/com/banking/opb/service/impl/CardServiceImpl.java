package com.banking.opb.service.impl;

import com.banking.opb.Utilities.BasicUtilities;
import com.banking.opb.domain.Card;
import com.banking.opb.service.CardService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {

    private static Map<String, Card> cardCache = new HashMap<>();

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


}
