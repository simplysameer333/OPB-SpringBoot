package com.banking.opb.clientapi;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.banking.opb.domain.Transaction;
import com.banking.opb.domain.TransactionRequest;

import lombok.Data;

@FeignClient(name="transaction", url="${obp.api.versionedUrl}")
public interface ObpTransactionApiClient {

    @GetMapping(value = "my/banks/{bank_id}/accounts/{account_id}/transactions", 
    		consumes = MediaType.APPLICATION_JSON_VALUE, 
    		headers = "Authorization=DirectLogin username=${obp.username},"
    				+ "password=${obp.password},consumer_key=${obp.consumerKey},token=${obp.token}")
    Transactions getTransactions(@PathVariable("bank_id") String bank_id, @PathVariable("account_id") String account_id);
    
    @PostMapping(value = "banks/{bank_id}/accounts/{account_id}/{view_id}/transaction-request-types/{trans_type}/transaction-requests", 
    		consumes = MediaType.APPLICATION_JSON_VALUE, 
    		headers = "Authorization=DirectLogin username=${obp.username},"
    				+ "password=${obp.password},consumer_key=${obp.consumerKey},token=${obp.token}")
    Transaction makeTransaction(@PathVariable("bank_id") String bank_id, @PathVariable("account_id") String account_id, 
    		@PathVariable("view_id") String view_id, @PathVariable("trans_type") String transType, @RequestBody TransactionRequest transReq);
	
	@Data
    class Transactions {
        private List<Transaction> transactions;
    }

}
