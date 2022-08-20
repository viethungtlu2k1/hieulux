package com.globits.da.dto;

import javax.persistence.Column;

import com.globits.core.domain.BaseObject;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Currency;

public class CurrencyDTO extends BaseObjectDto {
	public CurrencyDTO() {super();}
	public CurrencyDTO(Currency currency) {
		this.setId(currency.getId());
		this.name = currency.getName();
		this.code = currency.getCode();
		this.currencySymbols = currency.getCurrencySymbols();
	}
	
	private String name;
	private String code;
	private String currencySymbols;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCurrencySymbols() {
		return currencySymbols;
	}
	public void setCurrencySymbols(String currencySymbols) {
		this.currencySymbols = currencySymbols;
	}


}
