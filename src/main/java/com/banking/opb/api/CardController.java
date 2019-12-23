package com.banking.opb.api;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.banking.opb.domain.Bank;
import com.banking.opb.domain.Transaction;
import com.banking.opb.domain.TransactionRequest;
import com.banking.opb.domain.User;
import com.banking.opb.domain.custom.Card;
import com.banking.opb.exception.ApiRequestException;
import com.banking.opb.service.ICardService;
import com.banking.opb.service.ITransactionService;
import com.banking.opb.utilities.BasicUtilities;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class CardController {

    @Autowired
    private ICardService ICardServiceImpl;
    
    @Autowired
    private ITransactionService transactionServiceImpl;
    
    @PostMapping(value = "/api/card/addCard", consumes = "application/json", produces = "application/json")
    public Map<String, String> signUp(@RequestBody Card cardInfo) {
    	
        return Collections.singletonMap("response", ICardServiceImpl.addCard(cardInfo));
    }

    @PostMapping(value = "/api/card/setdefault", consumes = "application/json", produces = "application/json")
    public Map<String, Boolean> setDafault(@RequestParam String cardId) {
        boolean response;
        response = ICardServiceImpl.setDefault(cardId);
        return Collections.singletonMap("response", response);
    }

    @GetMapping(value = "/api/card/cardList")
    public List<Card> getCardsList() {
        return ICardServiceImpl.getCardList();
    }
    
    @GetMapping(value = "/api/transaction/transactionList/{bank_id}/{account_id}")
    public List<Transaction> getTransactions(@PathVariable("bank_id") String bank_id, @PathVariable("account_id") String account_id) {
        return transactionServiceImpl.getTransactions(bank_id, account_id);
    }
    
    @GetMapping(value = "/api/card/{cardId}")
    public Map<String, String> generatePassCode(@RequestParam String cardId) {
        if (BasicUtilities.isEmptyOrNullString(cardId))
            return Collections.singletonMap("response", "Authentication Failed, User Id not provided");
        else {
            ICardServiceImpl.generateCode(cardId);
            return Collections.singletonMap("response", "Code send");
        }
    }

    @PostMapping(value = "/api/card/verifyPassCode/", consumes = "application/json", produces = "application/json")
    public Map<String, String> verifyPassCode(@RequestBody Card cardInfo) {
        if (BasicUtilities.isEmptyOrNullString(cardInfo.getCardnumber()))
            return Collections.singletonMap("response", "Authentication Failed, User Id not provided");
        else {
            boolean response = ICardServiceImpl.validateCode(cardInfo.getCardnumber(), cardInfo.getLastCode());
            if (response)
                return Collections.singletonMap("response", "Authentication Successful");
            return Collections.singletonMap("response", "Authentication Failed");
        }
    }
    
    @PostMapping(value = "/api/transaction/makeTransaction/{bank_id}/{account_id}/{view_id}/{trans_type}", consumes = "application/json", produces = "application/json")
    public Transaction makeTransaction(@PathVariable("bank_id") String bank_id, @PathVariable("account_id") String account_id, 
    		@PathVariable("view_id") String view_id, @PathVariable("trans_type") String transType, 
    		@RequestBody TransactionRequest transReq) {
    	return transactionServiceImpl.makeTransaction(bank_id, account_id, view_id, transType, transReq);
    }

   /* @GetMapping(value = "/api/card/transactions/{cardId}")
    public ObpApiClient.Transactions getAllTransactionCard(@RequestParam String cardId) {
        Card cardInfor = ICardServiceImpl.getCardInfo(cardId);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "${obp.api.versionedUrl}/banks/{bankId}/accounts/{accountId}/owner/transactions",
                ObpApiClient.Transactions.class, cardInfor.getBranchId(), cardInfor.getAccountId()
        );
    }*/

}