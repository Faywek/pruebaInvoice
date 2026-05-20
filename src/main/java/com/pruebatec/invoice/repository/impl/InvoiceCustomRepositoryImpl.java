package com.pruebatec.invoice.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.persistence.Invoice;
import com.pruebatec.invoice.repository.InvoiceCustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class InvoiceCustomRepositoryImpl implements InvoiceCustomRepository{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
    public List<Invoice> findAllByCriteria(InvoiceDTO invoiceDTO) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
	    Root<Invoice> invoice = cq.from(Invoice.class);
	 
	    List<Predicate> predicates = new ArrayList<>();
	 
	    // Agregar filtros
	    if (invoiceDTO.getRutProveedor() != null && !invoiceDTO.getRutProveedor().isEmpty()) {
	        predicates.add(cb.equal(invoice.get("supplier").get("rut"), invoiceDTO.getRutProveedor()));
	    }
	    if (invoiceDTO.getFechaDesde() != null && invoiceDTO.getFechaHasta() != null) {
	        predicates.add(cb.between(invoice.get("issueDate"), invoiceDTO.getFechaDesde(), invoiceDTO.getFechaHasta()));
	    }
	    if (invoiceDTO.getInvoiceNumber() != null) {
	        predicates.add(cb.equal(invoice.get("invoiceNumber"), invoiceDTO.getInvoiceNumber()));
	    }
	    if (invoiceDTO.getFobValue() != null) {
	        predicates.add(cb.equal(invoice.get("fobValue"), invoiceDTO.getFobValue()));
	    }
	    if (invoiceDTO.getCurrencyType() != null) {
	        predicates.add(cb.equal(invoice.get("currencyType"), invoiceDTO.getCurrencyType()));
	    }
	    
        predicates.add(cb.equal(invoice.get("deleted"), false));
	 
	    cq.select(invoice).where(predicates.toArray(new Predicate[0])); 
	 
	    return entityManager.createQuery(cq).getResultList();
    }
	
	//Getter
	public EntityManager getEntityManager() {
		return entityManager;
	}

	//Setter
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
