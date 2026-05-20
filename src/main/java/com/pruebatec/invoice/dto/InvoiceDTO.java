package com.pruebatec.invoice.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDTO {

    private Long id;
    private String invoiceNumber;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String currencyType;
    private String incoterm;
    private String originCountry;
    private String destinationCountry;
    private Double fobValue;
    private Long supplierId; 
    private List<InvoiceDetailDTO> details;
    
    private String rutProveedor;
	private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    
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
	public Double getFobValue() {
		return fobValue;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public List<InvoiceDetailDTO> getDetails() {
		return details;
	}
	public String getRutProveedor() {
		return rutProveedor;
	}
	public LocalDate getFechaDesde() {
		return fechaDesde;
	}
	public LocalDate getFechaHasta() {
		return fechaHasta;
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
	public void setFobValue(Double fobValue) {
		this.fobValue = fobValue;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public void setDetails(List<InvoiceDetailDTO> details) {
		this.details = details;
	}
	
	public void setRutProveedor(String rutProveedor) {
		this.rutProveedor = rutProveedor;
	}
	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
