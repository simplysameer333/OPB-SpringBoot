package com.banking.opb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.opb.clientapi.ObpCardApiClient;
import com.banking.opb.clientapi.ObpTransactionApiClient;
import com.banking.opb.domain.custom.Card;
import com.banking.opb.domain.Transaction;
import com.banking.opb.domain.custom.SmsRequest;
import com.banking.opb.repo.IAccountDao;
import com.banking.opb.service.ICardService;
import com.banking.opb.service.TwilioSmsSender;
import com.banking.opb.utilities.BasicUtilities;

@Service
public class CardServiceImpl implements ICardService {

    private static Map<String, Card> cardCache = new HashMap<>();

    @Autowired
    private TwilioSmsSender twilioSmsSender;
    
    @Autowired
    private IAccountDao accountDao;
    
    @Autowired 
    private ObpTransactionApiClient obpTransactionApiClient;
    
    @Autowired 
    private ObpCardApiClient obpCardApiClient;
    
    @Override
    public String addCard(Card cardInfo) {
        /*if (BasicUtilities.isEmptyOrNullString(cardInfo.getAccountId())
                || BasicUtilities.isEmptyOrNullString(cardInfo.getCardNumber())
                || BasicUtilities.isEmptyOrNullString(cardInfo.getBranchId()))
            return "MandatoryMissing";*/
        if (cardCache.containsKey(cardInfo.getCardnumber()))
            return "CardExists";
        String cardStatus = "Unable to add the card";
        List<Card> cardList = obpCardApiClient.getCards().getCards();
        System.out.println("CardInfo:"+cardInfo+":api cards size:"+cardList.size());
        boolean cardVerify = false;
        for(Card card : cardList) {
        	System.out.println("api card no:"+card.getCardnumber());
        	if(cardInfo.getCardnumber().equals(card.getCardnumber())) { 
        		cardVerify = true;
        		break;
        	}
        }
        if(!cardVerify)
        	return "Invalid Card";
        return accountDao.addCard(cardInfo);
    }

    @Override
    public boolean setDefault(String card_id) {
        cardCache.get(card_id.trim()).setDefault(true);
        return true;
    }

    @Override
    public List<Card> getCardList() {
    	List<String> cards = accountDao.getCardsList();
    	List<Card> cardList = new ArrayList<Card>();
    	for(Card card : obpCardApiClient.getCards().getCards()) {
    		if(cards.contains(card.getCardnumber()))
    			cardList.add(card);
    	}
        return cardList;
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
