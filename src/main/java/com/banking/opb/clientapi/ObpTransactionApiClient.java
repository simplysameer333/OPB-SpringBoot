package com.banking.opb.clientapi;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.banking.opb.domain.Transaction;

import lombok.Data;

@FeignClient(name="transaction", url="${obp.api.versionedUrl}")
public interface ObpTransactionApiClient {

    @GetMapping(value = "my/banks/obp-bankx-m/accounts/simply_sameer_account_662550/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Authorization=DirectLogin username=simply_sameer,password=Justme@123,consumer_key=alp1mr1btifdpwv32qekdm1mkjwqvjom45gyi4in,token=eyJhbGciOiJIUzI1NiJ9.eyIiOiIifQ.5rt5-ybWPmg_Woo0lsUlPPD6_Nka1FYUmfY_kDdd3pI")
	//@GetMapping(value = "cards", consumes = MediaType.APPLICATION_JSON_VALUE)
    Transactions getTransactions();
	
	@Data
    class Transactions {
        private List<Transaction> transactions;
    }

}
