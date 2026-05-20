package com.pruebatec.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebatec.invoice.persistence.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, InvoiceCustomRepository{

}
