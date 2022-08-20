package com.globits.da.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_currency")
@XmlRootElement
public class Currency extends BaseObject {
	/**
	 * 
	 */
	public Currency() {
		super();
	}
	public Currency(String name,String code,String currencySymbols )
	{
		super();
		this.name = name;
		this.code =code;
		this.currencySymbols = currencySymbols;
	}
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	@Column(name = "currency_symbols")
	private String currencySymbols;

	public String getCurrencySymbols() {
		return currencySymbols;
	}

	public void setCurrencySymbols(String currencySymbols) {
		this.currencySymbols = currencySymbols;
	}

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
	

}
