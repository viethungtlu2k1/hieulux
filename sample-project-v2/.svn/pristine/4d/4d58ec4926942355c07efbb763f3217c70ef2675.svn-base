package com.globits.da.dto;

import java.util.Date;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Campaign;
import com.globits.da.domain.Product;

public class CampaignDto extends BaseObjectDto {
	private String name;
	private String code;
	private CampaignTypeDto campaignType;
	private Date startDate;
	private Date endDate;
	private Integer epc;
	private Double cvr;
	private Double commission;
	private Double approved_rate_30days;
	private ProductDto product;
	
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getEpc() {
		return epc;
	}
	public void setEpc(Integer epc) {
		this.epc = epc;
	}
	public Double getCvr() {
		return cvr;
	}
	public void setCvr(Double cvr) {
		this.cvr = cvr;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getApproved_rate_30days() {
		return approved_rate_30days;
	}
	public void setApproved_rate_30days(Double approved_rate_30days) {
		this.approved_rate_30days = approved_rate_30days;
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

	public CampaignTypeDto getCampaignType() {
		return campaignType;
	}
	public void setCampaignType(CampaignTypeDto campaignType) {
		this.campaignType = campaignType;
	}
	public CampaignDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CampaignDto(Campaign entity) {	
		if(entity != null) {
			this.setId(entity.getId());
			this.name = entity.getName();
			this.code = entity.getCode();
			if(entity.getCampaignType() != null) {
				this.campaignType = new CampaignTypeDto(entity.getCampaignType());
			}
			this.startDate = entity.getStartDate();
			this.endDate = entity.getEndDate();
			this.epc = entity.getEpc();
			this.cvr = entity.getCvr();
			this.commission = entity.getCommission();
			this.approved_rate_30days = entity.getApproved_rate_30days();
			if(entity.getProduct() != null) {
				this.product = new ProductDto(entity.getProduct());
			}
		}
	}
}
