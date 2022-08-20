package com.globits.da.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_category_space")
@XmlRootElement
public class DetailCategoryAdSpace extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "adspace_id")
	private AdSpace adSpace;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public DetailCategoryAdSpace() {
		super();
	}

	public DetailCategoryAdSpace(AdSpace adSpace, Category category) {
		super();
		this.adSpace = adSpace;
		this.category = category;
	}

	public AdSpace getAdSpace() {
		return adSpace;
	}

	public void setAdSpace(AdSpace adSpace) {
		this.adSpace = adSpace;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
