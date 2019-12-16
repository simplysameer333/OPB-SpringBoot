package com.banking.opb.clientapi;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banking.opb.clientapi.ObpBankMetaApiClient.Banks;
import com.banking.opb.domain.User;
import com.banking.opb.domain.custom.Card;

import lombok.Data;

@FeignClient(name="card", url="${obp.api.versionedUrl}")
public interface ObpCardApiClient {

	@RequestMapping(method = RequestMethod.GET, value = "cards", headers = "Authorization=DirectLogin username=simply_sameer,password=Justme@123,consumer_key=alp1mr1btifdpwv32qekdm1mkjwqvjom45gyi4in,token=eyJhbGciOiJIUzI1NiJ9.eyIiOiIifQ.5rt5-ybWPmg_Woo0lsUlPPD6_Nka1FYUmfY_kDdd3pI", consumes = MediaType.APPLICATION_JSON_VALUE)
    Cards getCards();
	
	@Data
    class Cards {
        private List<Card> cards;
    }

}
