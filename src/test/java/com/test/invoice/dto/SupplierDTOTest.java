package com.test.invoice.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.dto.SupplierDTO;

public class SupplierDTOTest {

    @Test
    void testGettersAndSetters() {
        SupplierDTO supplierDTO = new SupplierDTO();
 
        // Set values
        supplierDTO.setId(1L);
        supplierDTO.setName("Supplier A");
        supplierDTO.setRut("12345678-9");
        supplierDTO.setAddress("123 Main St");
        supplierDTO.setContact("contact@example.com");
 
        // Assert values
        assertEquals(1L, supplierDTO.getId());
        assertEquals("Supplier A", supplierDTO.getName());
        assertEquals("12345678-9", supplierDTO.getRut());
        assertEquals("123 Main St", supplierDTO.getAddress());
        assertEquals("contact@example.com", supplierDTO.getContact());
    }
}
