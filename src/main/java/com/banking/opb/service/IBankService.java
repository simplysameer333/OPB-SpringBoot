package com.banking.opb.service;

import com.banking.opb.clientapi.ObpBankMetaApiClient;
import com.banking.opb.domain.ATM;
import com.banking.opb.domain.Account;
import com.banking.opb.domain.Bank;
import com.banking.opb.domain.Branch;
import com.banking.opb.domain.custom.Customer;

import java.util.List;

public interface IBankService {
    List<Bank> allBanks();

    ObpBankMetaApiClient.Branches getBranches(String bankId);

    List<Bank> banksWithBranches();

    List<Branch> allBranches();

    Branch getBranch(String bankId, String branchId);

    List<ATM> allAtms();

    Account createAccount(Account accountRequest);

    Customer createCustomer(Customer customer);
}
