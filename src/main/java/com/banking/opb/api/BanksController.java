package com.banking.opb.api;

import com.banking.opb.clientapi.ObpBankMetaApiClient;
import com.banking.opb.domain.ATM;
import com.banking.opb.domain.Bank;
import com.banking.opb.domain.Branch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class BanksController {

    @Autowired
    private ObpBankMetaApiClient obpBankMetaApiClient;

    @GetMapping("/banks")
    public int allBanks() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();
        log.info("Fetching branches for " + allBanks);

        return allBanks.size();
    }

    @GetMapping("/validbanks")
    public List<Bank> banksWithBranches() {
        List<Bank> allBanks = new ArrayList<>();
        log.info("Fetching branches for " + allBanks);

        for (Bank bank: obpBankMetaApiClient.getBanks().getBanks()) {
            List<Branch> branches  = Collections.<Branch>emptyList();
            try {
                branches  = obpBankMetaApiClient.getBranches(bank.getId()).getBranches();
            } catch (Exception e) {
                //TODO: fix API not to return 400 if no branches are found for a bank
                //return Collections.<Branch>emptyList();
            }
            if (branches.size() > 0) {
                allBanks.add(bank);
            }
        }
        return allBanks;
    }

    @GetMapping("/branches")
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

    @GetMapping("/atms")
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
