package com.banking.opb.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.banking.opb.clientapi.ObpApiClient;
import com.banking.opb.domain.custom.Card;
import com.banking.opb.service.ICardService;
import com.banking.opb.utilities.BasicUtilities;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class CardController {

    @Autowired
    private ICardService ICardServiceImpl;

    @PostMapping(value = "/api/card/addCard", consumes = "application/json", produces = "application/json")
    public Map<String, String> signUp(@RequestBody Card cardInfo) {
        String response = "Failed";
        String username = ICardServiceImpl.addCard(cardInfo);
        if (username != null && !"MandatoryMissing".equals(username) && !"CardExists".equals(username))
            response = "Success";
        return Collections.singletonMap("response", response);
    }

    @PostMapping(value = "/api/card/setdefault", consumes = "application/json", produces = "application/json")
    public Map<String, Boolean> setDafault(@RequestParam String cardId) {
        boolean response;
        response = ICardServiceImpl.setDefault(cardId);
        return Collections.singletonMap("response", response);
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
        if (BasicUtilities.isEmptyOrNullString(cardInfo.getCardNumber()))
            return Collections.singletonMap("response", "Authentication Failed, User Id not provided");
        else {
            boolean response = ICardServiceImpl.validateCode(cardInfo.getCardNumber(), cardInfo.getLastCode());
            if (response)
                return Collections.singletonMap("response", "Authentication Successful");
            return Collections.singletonMap("response", "Authentication Failed");
        }
    }

    @GetMapping(value = "/api/card/transactions/{cardId}")
    public ObpApiClient.Transactions getAllTransactionCard(@RequestParam String cardId) {
        Card cardInfor = ICardServiceImpl.getCardInfo(cardId);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(
                "${obp.api.versionedUrl}/banks/{bankId}/accounts/{accountId}/owner/transactions",
                ObpApiClient.Transactions.class, cardInfor.getBranchId(), cardInfor.getAccountId()
        );
    }


}