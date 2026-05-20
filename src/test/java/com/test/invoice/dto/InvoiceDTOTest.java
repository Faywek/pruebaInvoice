package com.test.invoice.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.dto.InvoiceDTO;

public class InvoiceDTOTest {

	@Test
    void testGettersAndSetters() {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        
        invoiceDTO.setId(1L);
        invoiceDTO.setInvoiceNumber("INV-001");
        invoiceDTO.setIssueDate(LocalDate.of(2023, 5, 19));
        invoiceDTO.setDueDate(LocalDate.of(2023, 6, 19));
        invoiceDTO.setCurrencyType("USD");
        invoiceDTO.setFobValue(1000.00);
 
        assertEquals(1L, invoiceDTO.getId());
        assertEquals("INV-001", invoiceDTO.getInvoiceNumber());
        assertEquals(LocalDate.of(2023, 5, 19), invoiceDTO.getIssueDate());
        assertEquals(LocalDate.of(2023, 6, 19), invoiceDTO.getDueDate());
        assertEquals("USD", invoiceDTO.getCurrencyType());
        assertEquals(1000.00, invoiceDTO.getFobValue());
    }
}
