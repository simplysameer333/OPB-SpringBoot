package com.banking.opb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.opb.clientapi.ObpTransactionApiClient;
import com.banking.opb.domain.Transaction;
import com.banking.opb.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {
	
	@Autowired
	ObpTransactionApiClient obpTransactionApiClient;
	@Override
	public List<Transaction> getTransactions() {
				
		return obpTransactionApiClient.getTransactions().getTransactions();
	}
	@Override
	public Transaction makeTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

}
