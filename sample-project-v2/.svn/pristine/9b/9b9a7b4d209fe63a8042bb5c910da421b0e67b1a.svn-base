package com.globits.da.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_adspace_nationaly")
@XmlRootElement
public class DetailNationalyAdSpace extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "adspace_id")
	private AdSpace adSpace;

	@ManyToOne
	@JoinColumn(name = "nationaly_id")
	private Nationaly nationaly;

	public DetailNationalyAdSpace() {
		super();
	}

	public DetailNationalyAdSpace(AdSpace adSpace, Nationaly nationaly) {
		super();
		this.adSpace = adSpace;
		this.nationaly = nationaly;
	}

	public AdSpace getAdSpace() {
		return adSpace;
	}

	public void setAdSpace(AdSpace adSpace) {
		this.adSpace = adSpace;
	}

	public Nationaly getNationaly() {
		return nationaly;
	}

	public void setNationaly(Nationaly nationaly) {
		this.nationaly = nationaly;
	}

}
