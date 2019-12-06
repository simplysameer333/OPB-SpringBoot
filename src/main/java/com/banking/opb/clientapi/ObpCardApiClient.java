package com.banking.opb.clientapi;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import com.banking.opb.domain.custom.Card;

import lombok.Data;

@FeignClient(name="card", url="${obp.api.versionedUrl}")
public interface ObpCardApiClient {

	@GetMapping(value = "cards", consumes = MediaType.APPLICATION_JSON_VALUE)
    Cards getCardsForAccount();
	
	@Data
    class Cards {
        private List<Card> cards;
    }

}
