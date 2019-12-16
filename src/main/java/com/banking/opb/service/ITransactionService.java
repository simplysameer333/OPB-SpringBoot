package com.banking.opb.service;

import java.util.List;

import com.banking.opb.domain.Transaction;

public interface ITransactionService {
    List<Transaction> getTransactions();
    
    Transaction makeTransaction();
}
