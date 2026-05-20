package com.pruebatec.invoice.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supplier")
public class Supplier {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
