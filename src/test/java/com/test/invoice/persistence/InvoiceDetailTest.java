package com.test.invoice.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.persistence.Invoice;
import com.pruebatec.invoice.persistence.InvoiceDetail;

public class InvoiceDetailTest {

	@Test
    void testGettersAndSetters() {
        InvoiceDetail detail = new InvoiceDetail();
 
        // Set values
        detail.setId(1L);
        detail.setProductDescription("Product A");
        detail.setQuantity(5);
        detail.setUnitPrice(200.00);
 
        // Create an Invoice
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        detail.setInvoice(invoice);
 
        // Assert values
        assertEquals(1L, detail.getId());
        assertEquals("Product A", detail.getProductDescription());
        assertEquals(5, detail.getQuantity());
        assertEquals(200.00, detail.getUnitPrice());
        assertEquals(invoice, detail.getInvoice());
    }
}
