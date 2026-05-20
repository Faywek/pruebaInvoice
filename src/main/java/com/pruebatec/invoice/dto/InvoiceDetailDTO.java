package com.pruebatec.invoice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDetailDTO {

	private Long id;
    private String productDescription;
    private int quantity;
    private Double unitPrice;
	
    //Setters
    public Long getId() {
		return id;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	
	//Setter
	public void setId(Long id) {
		this.id = id;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
}
