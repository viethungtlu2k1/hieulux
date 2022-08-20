package com.globits.da.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.globits.core.domain.BaseObject; 
 

@Entity
@Table(name = "tbl_product")
@XmlRootElement
public class Product extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Column(name = "code")
	private String code;
	@Column(name = "price")
	private Double price;
	@Column(name = "main_image_url")
	private String mainImageUrl;
	@Column(name = "payout")
	private Double payout;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "nationaly_id")
	private Nationaly nationaly;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "campaign_type_id")
	private CampaignType campaignType;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "currency_price_id")
	private Currency currency_price;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "currency_payout_id")
	private Currency currency_payout;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<LandingPage> landingPage;

	public String getMainImageUrl() {
		return mainImageUrl;
	}

	public void setMainImageUrl(String mainImageUrl) {
		this.mainImageUrl = mainImageUrl;
	}
	
	
	public Currency getCurrencyPricce() {		
		return currency_price;
	}
	
	public void setCurrencyPrice(Currency currency_price) {		
		this.currency_price = currency_price;
	}
	
	public Currency getCurrencyPayout() {		
		return currency_payout;
	}
	
	public void setCurrencyPayout(Currency currency_payout) {		
		this.currency_payout = currency_payout;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CampaignType getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(CampaignType campaignType) {
		this.campaignType = campaignType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPayout() {
		return payout;
	}

	public void setPayout(Double payout) {
		this.payout = payout;
	}

	public Nationaly getNationaly() {
		return nationaly;
	}

	public void setNationaly(Nationaly nationaly) {
		this.nationaly = nationaly;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
