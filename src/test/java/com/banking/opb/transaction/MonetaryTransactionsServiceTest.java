package com.banking.opb.transaction;

import com.banking.opb.AbstractTestSupport;
import com.banking.opb.clientapi.ObpApiClient;
import com.banking.opb.domain.Account;
import com.banking.opb.domain.TransactionRequest;
import com.banking.opb.domain.Transaction;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonetaryTransactionsServiceTest extends AbstractTestSupport {

    @Autowired private ObpApiClient obpApiClient;

    @Test
    public void fetchTransactionListOk() throws Exception {
        List<Account> accounts = obpApiClient.getPrivateAccountsNoDetails();
        Assert.assertTrue(accounts.size() > 0);

        String bankId = accounts.get(0).getBankId();
        String accountIdOne = accounts.get(0).getId();
        ObpApiClient.TransactionRequestTypes txTypes = obpApiClient.getTransactionTypes(bankId, accountIdOne);

        TransactionRequest transactionRequest = new TransactionRequest(
                new TransactionRequest.DestAccount(bankId, accountIdOne), Money.of(CurrencyUnit.EUR, 5), "some description");

        String result = obpApiClient.initiateTransaction(bankId, accounts.get(1).getId(), "SANDBOX_TAN", transactionRequest);

        List<Transaction> transactions = obpApiClient.getTransactionsForAccount(bankId, accountIdOne).getTransactions();
        Assert.assertTrue(transactions.size() > 0);
    }

}