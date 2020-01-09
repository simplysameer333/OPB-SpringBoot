package com.banking.opb.service;

import java.util.List;

import com.banking.opb.domain.Transaction;
import com.banking.opb.domain.custom.Card;

public interface ICardService {
    String addCard(Card userInfo);
    boolean setDefault(String card_id);
    Card getCardInfo(String card_id);
    List<Card> getCardList(String sessionemail);
    void generateCode(String card_id);
    boolean validateCode(String card_id, int passCode);
}
