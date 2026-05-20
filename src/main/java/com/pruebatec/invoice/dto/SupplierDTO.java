package com.pruebatec.invoice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDTO {

	private Long id;
	private String name;
	private String rut;
	private String address;
	private String contact;
	
	//Getters
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRut() {
		return rut;
	}
	public String getAddress() {
		return address;
	}
	public String getContact() {
		return contact;
	}
	
	//Setters
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
