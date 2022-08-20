package com.globits.sample.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.globits.core.domain.BaseObject;
import com.globits.sample.domain.Student;


public class StudentDto{
	private UUID id;
	
	private String lastName;
	private String firstName;
	
	private String phoneNumber;

	private String location;
	
	private String code;
	private String description;

	private StudentClassDto studentClass;
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public StudentClassDto getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(StudentClassDto studentClass) {
		this.studentClass = studentClass;
	}

	public StudentDto() {
		
	}
	
	public StudentDto(Student domain) {
		this.id= domain.getId();
		this.code = domain.getCode();
		this.description=domain.getDescription();
		this.firstName=domain.getFirstName();
		this.lastName=domain.getLastName();
		this.location = domain.getLocation();
		this.phoneNumber = domain.getPhoneNumber();
		if(domain.getStudentClass()!=null) {
			this.studentClass = new StudentClassDto();
			this.studentClass.setCode(domain.getStudentClass().getCode());
			this.studentClass.setName(domain.getStudentClass().getName());
			this.studentClass.setId(domain.getStudentClass().getId());
		}
	}
}
