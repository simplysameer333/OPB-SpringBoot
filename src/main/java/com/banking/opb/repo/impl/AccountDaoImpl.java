package com.banking.opb.repo.impl;

import org.springframework.stereotype.Service;

import com.banking.opb.domain.custom.Card;
import com.banking.opb.repo.IAccountDao;

@Service
public class AccountDaoImpl implements IAccountDao {

	@Override
	public String addCard(Card userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setDefault(String card_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Card getCardInfo(String card_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateCode(String card_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateCode(String card_id, int passCode) {
		// TODO Auto-generated method stub
		return false;
	}

}
