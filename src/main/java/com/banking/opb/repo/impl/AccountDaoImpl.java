package com.banking.opb.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.banking.opb.domain.custom.Card;
import com.banking.opb.repo.IAccountDao;
import com.banking.opb.repo.SqlQueries;

@Service
public class AccountDaoImpl implements IAccountDao {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private SqlQueries queries;
	
	@Override
	public String addCard(Card cardInfo) {

		String sqlquery = queries.getQueries().get("checkCard");
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("cardnumber", cardInfo.getCardnumber());
		
		
		int count  = namedParameterJdbcTemplate.queryForObject(sqlquery, namedParameters, Integer.class);
		if (count > 0)
			return "CardExists";
		
		sqlquery = queries.getQueries().get("addCard");
		namedParameters = new MapSqlParameterSource()
				.addValue("cardnumber", cardInfo.getCardnumber())
				.addValue("expirydate", cardInfo.getExpirydate())
				.addValue("cvv", cardInfo.getCvv())
				.addValue("nameoncard", String.valueOf(cardInfo.getNameoncard()));
		
		
		count  = namedParameterJdbcTemplate.update(sqlquery, namedParameters);
		if (count > 0)
			return "Success";
		return null;
	}

	@Override
	public List<String> getCardsList() {
		String sqlquery = queries.getQueries().get("cardList");
		List<String> cards = new ArrayList<String>();
		List<Map<String,Object>> cardList;
		cardList  = namedParameterJdbcTemplate.queryForList(sqlquery, new MapSqlParameterSource());
		/*JSONArray json_arr=new JSONArray();
	    for (Map<String, Object> map : cardList) {
	        JSONObject json_obj=new JSONObject();
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            try {
	                json_obj.put(key,value);
	            } catch (JSONException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }                           
	        }
	        json_arr.put(json_obj);
	    }
		return json_arr.toString();*/
		for (Map<String, Object> map : cardList) {
			cards.add(map.get("CARDNUMBER").toString());
		}
		return cards;
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
