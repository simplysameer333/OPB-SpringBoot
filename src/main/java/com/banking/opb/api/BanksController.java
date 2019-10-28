package com.banking.opb.api;

import com.banking.opb.clientapi.ObpBankMetaApiClient;
import com.banking.opb.domain.ATM;
import com.banking.opb.domain.Account;
import com.banking.opb.domain.Bank;
import com.banking.opb.domain.Branch;
import com.banking.opb.domain.custom.Customer;
import com.banking.opb.exception.ApiRequestException;
import com.banking.opb.service.IBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
public class BanksController {

    @Autowired
    private IBankService bankService;

    @GetMapping("/api/banks")
    public List<Bank> allBanks() {
        List<Bank> allBanks;
        try {
            allBanks = bankService.allBanks();
            log.info("Fetching branches for " + allBanks);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
        return allBanks;
    }

    @GetMapping("/api/banks/banksWithBranches")
    public List<Bank> banksWithBranches() {
        List<Bank> allBanks;
        try {
            allBanks = bankService.banksWithBranches();
            return allBanks;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
    }

    @GetMapping("/api/branches")
    public List<Branch> allBranches() {
        List<Branch> allBranches;
        try {
            allBranches = bankService.allBranches();
            return allBranches;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "api/banks/{bankId}/branches") // just to put another option
    public ObpBankMetaApiClient.Branches getBranches(@PathVariable("bankId") String bankId) {
        return bankService.getBranches(bankId);
    }

    @GetMapping("api/banks/{bankId}/branches/{branchId}")
    Branch getBranch(@PathVariable("bankId") String bankId, @PathVariable("branchId") String branchId) {
        List<Branch> allBranches;
        try {
            return bankService.getBranch(bankId, branchId);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
    }

    @GetMapping("/api/atms")
    public List<ATM> allAtms() {
        List<ATM> allAtms;
        try {
            allAtms = bankService.allAtms();
            return allAtms;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
    }

    @PostMapping("/api/bank/createAccount")
    public Account createAccount(@RequestBody Account accountRequest) {
        Account createdAccount;
        try {
            createdAccount = bankService.createAccount(accountRequest);
            return createdAccount;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
    }

    @PostMapping("/api/banks/{bankId}/customers")
    public Customer createCustomer(@PathVariable("bankId") String bankId, @RequestBody Customer customer) {
        Customer customerAccount;
        try {
            customerAccount = bankService.createCustomer(customer);
            return customerAccount;
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), e);
        }
    }
}
