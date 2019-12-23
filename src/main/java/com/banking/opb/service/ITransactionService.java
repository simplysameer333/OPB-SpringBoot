package com.banking.opb.service;

import java.util.List;

import com.banking.opb.domain.Transaction;
import com.banking.opb.domain.TransactionRequest;

public interface ITransactionService {
    List<Transaction> getTransactions(String bankId, String accountId);
    
    Transaction makeTransaction(String bankId, String accountId, String viewId, String transType, TransactionRequest transReq);
}
