package com.globits.da.dto;

import java.util.Set;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.Constants;
import com.globits.da.domain.AdSpace;
import com.globits.da.domain.Category;
import com.globits.da.domain.Nationaly;

public class AdSpaceDto extends BaseObjectDto {

	private String name;
	private Integer channel;
	private String network;
	private String url_page;
	private String follower;
	private String likeAmount;
	private String description;
	private String experience;
	private String channelName;
	private String urlWebsite;
	private String urlYoutube;
	private String nameYoutube;
	private String subscribeYoutube;
	private Integer contextualAdvertisingSystem;
	private String contextualAdvertisingSystemName;
	private Set<Nationaly> nationaly;
	private Set<Category> category;
	
	public AdSpaceDto() {
		super();
	}

	public AdSpaceDto(AdSpace entity) {
		super();
		if (entity != null) {
			this.setId(entity.getId());
			this.name = entity.getName();
			this.channel = entity.getChannel();
			this.description = entity.getDescription();
			this.experience = entity.getExperience();

			if (Constants.ChannelAds.Webiste.getValue() == this.channel) {
				this.channelName = "Website";
			} else if (Constants.ChannelAds.Contextual_Advertiser.getValue() == this.channel) {
				this.channelName = "Contextual Advertiser";
			} else if (Constants.ChannelAds.Social_Netword.getValue() == this.channel) {
				this.channelName = "Social Netword";
			} else if (Constants.ChannelAds.Youtube_channel.getValue() == this.channel) {
				this.channelName = "Youtube Channel";
			}

			if (this.channel != null) {
				switch (this.channel) {
				case 1:
					this.urlWebsite = entity.getUrlWebsite();
					break;
				case 2:
					this.contextualAdvertisingSystem = entity.getContextualAdvertisingSystem();
					break;
				case 3:
					this.network = entity.getNetwork();
					this.url_page = entity.getUrlPage();
					this.follower = entity.getFollower();
					this.likeAmount = entity.getLikeAmount();
					break;
				case 4:
					this.urlYoutube = entity.getUrlYoutube();
					this.nameYoutube = entity.getNameYoutube();
					this.subscribeYoutube = entity.getSubscribeYoutube();
					break;
				default:
					break;
				}
			}

			if (this.contextualAdvertisingSystem != null) {
				switch (this.contextualAdvertisingSystem) {
				case 1:
					this.contextualAdvertisingSystemName = "Bing Ads";
					break;
				case 2:
					this.contextualAdvertisingSystemName = "Google Ads";
					break;
				case 3:
					this.contextualAdvertisingSystemName = "Facebook Ads";
					break;
				case 4:
					this.contextualAdvertisingSystemName = "Tiktok Ads";
					break;
				case 5:
					this.contextualAdvertisingSystemName = "Zalo Ads";
					break;
				default:
					this.contextualAdvertisingSystemName = "No Ads";
					break;
				}
			}
			this.nationaly = entity.getNationaly();
			this.category = entity.getCategory();
		}
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

	public String getUrl_page() {
		return url_page;
	}

	public void setUrl_page(String url_page) {
		this.url_page = url_page;
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
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
		return contextualAdvertisingSystem;
	}

	public void setContextualAdvertisingSystem(Integer contextualAdvertisingSystem) {
		this.contextualAdvertisingSystem = contextualAdvertisingSystem;
	}

	public String getContextualAdvertisingSystemName() {
		return contextualAdvertisingSystemName;
	}

	public void setContextualAdvertisingSystemName(String contextualAdvertisingSystemName) {
		this.contextualAdvertisingSystemName = contextualAdvertisingSystemName;
	}

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

}
