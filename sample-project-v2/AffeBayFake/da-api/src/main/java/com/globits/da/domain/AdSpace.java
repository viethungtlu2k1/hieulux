package com.globits.da.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_adspace")
@XmlRootElement
public class AdSpace extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name; // 1,2,3,4
	@Column(name = "channel")
	private Integer channel;
//	Webiste(1), // website
//	Contextual_Advertiser(2), // khen hquang cao
//	Social_Netword(3), // mang xa hoi
//	Youtube_channel(4)// youtube 

	@Column(name = "network") // 3
	private String network;

	@Column(name = "url_page") // 3
	private String urlPage;

	@Column(name = "follower") // 3
	private String follower;

	@Column(name = "like_amount") // 3
	private String likeAmount;

	@Column(name = "description") // 3,4,1,2
	private String description;

	@Column(name = "experiencet") // 3,4,1,2
	private String experience;

	@OneToMany
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "nationaly_id")
	private Set<Nationaly> nationaly; // 3,4,1,2

	@OneToMany
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "category_id")
	private Set<Category> category; // 1,2,3,4

	@Column(name = "url_website") // 1
	private String urlWebsite;

	@Column(name = "url_youtube") // 4
	private String urlYoutube;

	@Column(name = "name_youtube") // 4
	private String nameYoutube;

	@Column(name = "subscribe_youtube") // 4
	private String subscribeYoutube;

	@Column(name = "contextual_dvertising_ystem") // 2
	private Integer ContextualAdvertisingSystem; // 1 bing ads, 2 google ads, 3 facebook ads,4 tiktok ads,5 zalo ads

	public Set<Nationaly> getNationaly() {
		return nationaly;
	}

	public void setNationaly(Set<Nationaly> nationaly) {
		this.nationaly = nationaly;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public String getUrlWebsite() {
		return urlWebsite;
	}

	public void setUrlWebsite(String urlWebsite) {
		this.urlWebsite = urlWebsite;
	}

	public String getUrlYoutube() {
		return urlYoutube;
	}

	public void setUrlYoutube(String urlYoutube) {
		this.urlYoutube = urlYoutube;
	}

	public String getNameYoutube() {
		return nameYoutube;
	}

	public void setNameYoutube(String nameYoutube) {
		this.nameYoutube = nameYoutube;
	}

	public String getSubscribeYoutube() {
		return subscribeYoutube;
	}

	public void setSubscribeYoutube(String subscribeYoutube) {
		this.subscribeYoutube = subscribeYoutube;
	}

	public Integer getContextualAdvertisingSystem() {
		return ContextualAdvertisingSystem;
	}

	public void setContextualAdvertisingSystem(Integer contextualAdvertisingSystem) {
		ContextualAdvertisingSystem = contextualAdvertisingSystem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getUrlPage() {
		return urlPage;
	}

	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}

	public String getFollower() {
		return follower;
	}

	public void setFollower(String follower) {
		this.follower = follower;
	}

	public String getLikeAmount() {
		return likeAmount;
	}

	public void setLikeAmount(String likeAmount) {
		this.likeAmount = likeAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

}
