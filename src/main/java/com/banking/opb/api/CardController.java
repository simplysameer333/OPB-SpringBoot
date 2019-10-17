package com.banking.opb.api;

import com.banking.opb.Utilities.BasicUtilities;
import com.banking.opb.clientapi.ObpApiClient;
import com.banking.opb.domain.Card;
import com.banking.opb.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardServiceImpl;

    @PostMapping(value = "/api/card/addCard", consumes = "application/json", produces = "application/json")
    public Map<String, String> sigcnUp(@RequestBody Card cardInfo) {
        String response = "Failed";
        String username = cardServiceImpl.addCard(cardInfo);
        if (username != null && !"MandatoryMissing".equals(username) && !"CardExists".equals(username))
            response = "Success";
        return Collections.singletonMap("response", response);
    }

    @PostMapping(value = "/api/card/setdefault", consumes = "application/json", produces = "application/json")
    public Map<String, Boolean> setDafault(@RequestParam String cardId) {
        boolean response = false;
        response = cardServiceImpl.setDefault(cardId);
        return Collections.singletonMap("response", response);
    }

    @GetMapping(value = "/api/card/{cardId}")
    public Map<String, String> generatePassCode(@RequestParam String cardId) {
        if (BasicUtilities.isEmptyOrNullString(cardId))
            return Collections.singletonMap("response", "Authentication Failed, User Id not provided");
        else {
            cardServiceImpl.generateCode(cardId);
            return Collections.singletonMap("response", "Code send");
        }
    }

    @PostMapping(value = "/api/card/verifyPassCode/", consumes = "application/json", produces = "application/json")
    public Map<String, String> verifyPassCode(@RequestBody Card cardInfo) {
        if (BasicUtilities.isEmptyOrNullString(cardInfo.getCardNumber()))
            return Collections.singletonMap("response", "Authentication Failed, User Id not provided");
        else {
            boolean response = cardServiceImpl.validateCode(cardInfo.getCardNumber(), cardInfo.getLastCode());
            if (response)
                return Collections.singletonMap("response", "Authentication Successful");
            return Collections.singletonMap("response", "Authentication Failed");
        }
    }

    @GetMapping(value = "/api/card/transactions/{cardId}")
    public ObpApiClient.Transactions getAllTransactionCard(@RequestParam String cardId) {
        Card cardInfor = cardServiceImpl.getCardInfo(cardId);
        RestTemplate restTemplate = new RestTemplate();
        ObpApiClient.Transactions result =
                restTemplate.getForObject(
                        "${obp.api.versionedUrl}/banks/{bankId}/accounts/{accountId}/owner/transactions",
                        ObpApiClient.Transactions.class, cardInfor.getBranchId(), cardInfor.getAccountId()
                );
        return result;
    }


}