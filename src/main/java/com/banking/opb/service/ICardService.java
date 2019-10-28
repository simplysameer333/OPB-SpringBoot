package com.banking.opb.service;

import com.banking.opb.domain.custom.Card;

public interface ICardService {
    String addCard(Card userInfo);
    boolean setDefault(String card_id);
    Card getCardInfo(String card_id);
    void generateCode(String card_id);
    boolean validateCode(String card_id, int passCode);
}
