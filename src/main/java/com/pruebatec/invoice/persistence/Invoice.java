package com.pruebatec.invoice.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice")
public class Invoice {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String invoiceNumber;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String currencyType;
    private String incoterm;
    private String originCountry;
    private String destinationCountry;
    private LocalDateTime creationDate;
    private Double fobValue;
    
    private Boolean deleted = false;
 
	@ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
 
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceDetail> details = new ArrayList<>();

    //Getters
	public Long getId() {
		return id;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public Double getFobValue() {
		return fobValue;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public List<InvoiceDetail> getDetails() {
		return details;
	}
	
    public Boolean getDeleted() {
		return deleted;
	}

	//Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setDetails(List<InvoiceDetail> details) {
		this.details = details;
	}
    
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
