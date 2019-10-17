package com.banking.opb.service;

import com.banking.opb.domain.Card;

public interface CardService {
    String addCard(Card userInfo);
    boolean setDefault(String card_id);
    Card getCardInfo(String card_id);

    void generateCode(String card_id);

    boolean validateCode(String card_id, int passCode);
}
