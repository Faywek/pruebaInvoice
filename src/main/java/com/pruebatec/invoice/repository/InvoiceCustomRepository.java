package com.pruebatec.invoice.repository;

import java.util.List;

import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.persistence.Invoice;

public interface InvoiceCustomRepository {

	List<Invoice> findAllByCriteria(InvoiceDTO invoiceDTO);
}
