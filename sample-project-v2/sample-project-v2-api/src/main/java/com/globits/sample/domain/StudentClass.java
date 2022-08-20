package com.globits.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_student_class")
public class StudentClass extends BaseObject{
	
	@Column(name="code")
	private String code;
	@Column(name="name")
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
