package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.CampaignType;
import com.globits.da.domain.Category;
import com.globits.da.domain.Currency;
import com.globits.da.domain.Nationaly;
import com.globits.da.domain.Product;

public class ProductDto extends BaseObjectDto {
	private String name;
	private String code;  
	private Double price; 
	private String mainImageUrl;
	private Double payout; 
	private Nationaly nationaly;
	private CampaignType campaignType;
	private Category category;
	private Currency currencyPrice;
	private Currency currencyPayout;
	private String description;
	
	public ProductDto() {
		super();
	}
	
	public ProductDto(Product entity) {
		if (entity !=  null) {
			this.setId(entity.getId());
			this.name = entity.getName();
			this.code = entity.getCode();
			this.price = entity.getPrice();
			this.mainImageUrl = entity.getMainImageUrl();
			this.payout = entity.getPayout();
			this.nationaly = entity.getNationaly();
			this.campaignType = entity.getCampaignType();
			this.category = entity.getCategory();
			this.currencyPrice = entity.getCurrencyPricce();
			this.currencyPayout = entity.getCurrencyPayout();
			this.description = entity.getDescription();
		}
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return this.price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMainImageUrl() {
		return this.mainImageUrl;
	}
	
	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}

	public Double getPayout() {
		return this.payout;
	}
	
	public void setPayout(Double payout) {
		this.payout = payout;
	}

	public Nationaly getNationaly() {
		return this.nationaly;
	}
	
	public void setNationaly(Nationaly nationaly) {
		this.nationaly = nationaly;
	}

	public CampaignType getCampaignType() {
		return this.campaignType;
	}
	
	public void setCampaignType(CampaignType campaignType) {
		this.campaignType = campaignType;
	}

	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}

	public Currency getCurrencyPrice() {
		return this.currencyPrice;
	}
	
	public void setCurrencyPrice(Currency currencyPrice) {
		this.currencyPrice = currencyPrice;
	}
	
	public Currency getCurrencyPayout() {
		return this.currencyPayout;
	}
	
	public void setCurrencyPayout(Currency currencyPayout) {
		this.currencyPayout = currencyPayout;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
