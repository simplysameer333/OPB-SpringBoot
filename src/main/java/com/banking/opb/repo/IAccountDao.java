package com.banking.opb.repo;

import com.banking.opb.domain.custom.Card;

public interface IAccountDao {
	String addCard(Card userInfo);
    boolean setDefault(String card_id);
    Card getCardInfo(String card_id);
    void generateCode(String card_id);
    boolean validateCode(String card_id, int passCode);
}