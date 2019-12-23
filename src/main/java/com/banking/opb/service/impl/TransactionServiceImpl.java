package com.banking.opb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.opb.clientapi.ObpTransactionApiClient;
import com.banking.opb.domain.Transaction;
import com.banking.opb.domain.TransactionRequest;
import com.banking.opb.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	ObpTransactionApiClient obpTransactionApiClient;
	@Override
	public List<Transaction> getTransactions(String bankId, String accountId) {
				
		return obpTransactionApiClient.getTransactions(bankId,accountId).getTransactions();
	}
	@Override
	public Transaction makeTransaction(String bankId, String accountId, String viewId, String transType, TransactionRequest transReq) {

		return obpTransactionApiClient.makeTransaction(bankId, accountId, viewId, transType, transReq);
	}

}
