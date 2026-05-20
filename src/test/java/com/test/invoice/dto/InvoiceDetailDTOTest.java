package com.test.invoice.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.dto.InvoiceDetailDTO;

public class InvoiceDetailDTOTest {

    @Test
    void testGettersAndSetters() {
        InvoiceDetailDTO detailDTO = new InvoiceDetailDTO();
 
        // Set values
        detailDTO.setId(1L);
        detailDTO.setProductDescription("Product A");
        detailDTO.setQuantity(5);
        detailDTO.setUnitPrice(200.00);
 
        // Assert values
        assertEquals(1L, detailDTO.getId());
        assertEquals("Product A", detailDTO.getProductDescription());
        assertEquals(5, detailDTO.getQuantity());
        assertEquals(200.00, detailDTO.getUnitPrice());
    }
}
