package com.banking.opb.service.impl;

import com.banking.opb.Utilities.BasicUtilities;
import com.banking.opb.clientapi.ObpApiClient;
import com.banking.opb.clientapi.ObpBankMetaApiClient;
import com.banking.opb.domain.ATM;
import com.banking.opb.domain.Account;
import com.banking.opb.domain.Bank;
import com.banking.opb.domain.Branch;
import com.banking.opb.domain.custom.Customer;
import com.banking.opb.domain.custom.UserLoginInformation;
import com.banking.opb.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements IBankService {

    @Autowired
    private ObpBankMetaApiClient obpBankMetaApiClient;

    @Autowired
    private ObpApiClient obpApiClient;

    @Override
    public List<Bank> allBanks() {
        return obpBankMetaApiClient.getBanks().getBanks();
    }

    @Override
    public ObpBankMetaApiClient.Branches getBranches(String bankId) {
        return obpBankMetaApiClient.getBranches(bankId);
    }

    @Override
    public List<Bank> banksWithBranches() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();

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

    @Override
    public List<Branch> allBranches() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();

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

    @Override
    public Branch getBranch(String bankId, String branchId) {
        return obpBankMetaApiClient.getBranch(bankId, branchId);
    }

    @Override
    public List<ATM> allAtms() {
        List<Bank> allBanks = obpBankMetaApiClient.getBanks().getBanks();
        return allBanks.stream().map(bank -> {
            try {
                return obpBankMetaApiClient.getAtms(bank.getId()).getAtms();
            } catch (Exception e) {
                //TODO: fix API not to return 400 if no branches are found for a bank
                return Collections.<ATM>emptyList();
            }
        }).filter(branches -> branches.size() > 0)   //exclude empty branch lists
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public Account createAccount(Account accountRequest) {
        UserLoginInformation user = (UserLoginInformation) BasicUtilities.session().getAttribute("activeuser");
        String accountId = user.getUsername().concat("_account_")
                .concat(String.valueOf(BasicUtilities.getRandomNumberInRange()));
        return obpApiClient.createAccount(accountRequest.getBankId(), accountId, accountRequest);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        SecurityContextHolder.setContext(new SecurityContextImpl(
                new UsernamePasswordAuthenticationToken("simply_sameer",
                        "eyJhbGciOiJIUzI1NiJ9.eyIiOiIifQ.tthEo5tR9b3fBj0w8A7okSCfmtpN_qEMcYKK1J_8zGo")));
        return obpApiClient.createCustomer(customer.getBankId(), customer);
    }
}
