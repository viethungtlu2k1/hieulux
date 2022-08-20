package com.globits.da.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;
@Entity
@Table(name = "tbl_campaign")
@XmlRootElement
public class Campaign extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	
	@ManyToOne
	@JoinColumn(name="campaign_type_id")
	private CampaignType campaignType;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "EPC")
	private Integer	 epc;
	
	@Column(name = "CVR")
	private Double cvr;
	
	@Column(name = "commission")
	private Double commission;
	
	@Column(name = "Approved_Rate_30days")
	private Double approved_rate_30days;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	public CampaignType getCampaignType() {
		return campaignType;
	}
	public void setCampaignType(CampaignType campaignType) {
		this.campaignType = campaignType;
	}
	
	
}
