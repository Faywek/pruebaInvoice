package com.pruebatec.invoice.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String productDescription;
    private int quantity;
    private Double unitPrice;
 
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    //Getters
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

	public Invoice getInvoice() {
		return invoice;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	//Setters
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
