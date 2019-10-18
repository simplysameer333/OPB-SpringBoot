package com.banking.opb.api;

import com.banking.opb.clientapi.ObpBankMetaApiClient;
import com.banking.opb.domain.ATM;
import com.banking.opb.domain.Bank;
import com.banking.opb.domain.Branch;
import com.banking.opb.exception.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@Slf4j
@CrossOrigin
public class BanksController {

    @Autowired
    private ObpBankMetaApiClient obpBankMetaApiClient;

    @GetMapping("/api/banks")
    public List<Bank> allBanks() {
        List<Bank> allBanks;
        try {
            allBanks = obpBankMetaApiClient.getBanks().getBanks();
            log.info("Fetching branches for " + allBanks);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
        return allBanks;
    }

    @GetMapping("/api/validbanks")
    public List<Bank> banksWithBranches() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();
        log.info("Fetching branches for " + allBanks);

        return allBanks.stream().map(bank -> {
            try {
                if (obpBankMetaApiClient.getBranches(bank.getId()).getBranches().size() > 0)
                    return bank;
                return null;
            } catch (Exception e) {
                //TODO: fix API not to return 400 if no branches are found for a bank
                return null;
            }
        }).filter(Objects::nonNull)   //exclude empty branch lists
                .collect(Collectors.toList());
    }

    @GetMapping("/api/branches")
    public List<Branch> allBranches() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();
        log.info("Fetching branches for " + allBanks);

        return allBanks.stream().map(bank -> {
            try {
                return obpBankMetaApiClient.getBranches(bank.getId()).getBranches();
            } catch (Exception e) {
                //TODO: fix API not to return 400 if no branches are found for a bank
                return Collections.<Branch>emptyList();
            }
        }).filter(branches -> branches.size() > 0)   //exclude empty branch lists
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    @GetMapping("/api/atms")
    public List<ATM> allAtms() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();
        return allBanks.stream().map(bank -> {
                    try {
                        return obpBankMetaApiClient.getAtms(bank.getId()).getAtms();
                    } catch (Exception e) {
                        //TODO: fix API not to return 400 if no branches are found for a bank
                        return Collections.<ATM>emptyList();
                    }
                })
                .filter(branches -> branches.size() > 0)   //exclude empty branch lists
                .flatMap(Collection::stream).collect(Collectors.toList());

    }
}
