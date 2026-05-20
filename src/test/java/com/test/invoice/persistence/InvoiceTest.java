package com.test.invoice.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.persistence.Invoice;
import com.pruebatec.invoice.persistence.InvoiceDetail;
import com.pruebatec.invoice.persistence.Supplier;

public class InvoiceTest {

	@Test
    void testGettersAndSetters() {
        Invoice invoice = new Invoice();
        
        // Set values
        invoice.setId(1L);
        invoice.setInvoiceNumber("INV-001");
        invoice.setIssueDate(LocalDate.of(2023, 5, 19));
        invoice.setDueDate(LocalDate.of(2023, 6, 19));
        invoice.setCurrencyType("USD");
        invoice.setIncoterm("FOB");
        invoice.setOriginCountry("USA");
        invoice.setDestinationCountry("Chile");
        invoice.setCreationDate(LocalDateTime.now());
        invoice.setFobValue(1000.00);
        invoice.setDeleted(false);
        
        // Create a Supplier
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        invoice.setSupplier(supplier);
        
        // Create InvoiceDetail list
        List<InvoiceDetail> details = new ArrayList<>();
        InvoiceDetail detail = new InvoiceDetail();
        detail.setId(1L);
        detail.setProductDescription("Product A");
        detail.setQuantity(5);
        detail.setUnitPrice(200.00);
        detail.setInvoice(invoice);
        details.add(detail);
        invoice.setDetails(details);
 
        // Assert values
        assertEquals(1L, invoice.getId());
        assertEquals("INV-001", invoice.getInvoiceNumber());
        assertEquals(LocalDate.of(2023, 5, 19), invoice.getIssueDate());
        assertEquals(LocalDate.of(2023, 6, 19), invoice.getDueDate());
        assertEquals("USD", invoice.getCurrencyType());
        assertEquals("FOB", invoice.getIncoterm());
        assertEquals("USA", invoice.getOriginCountry());
        assertEquals("Chile", invoice.getDestinationCountry());
        assertEquals(1000.00, invoice.getFobValue());
        assertEquals(false, invoice.getDeleted());
        assertEquals(supplier, invoice.getSupplier());
        assertEquals(1, invoice.getDetails().size());
        assertEquals(detail, invoice.getDetails().get(0));
    }
}
