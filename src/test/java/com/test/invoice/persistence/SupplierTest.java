package com.test.invoice.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.persistence.Supplier;

public class SupplierTest {

	@Test
    void testGettersAndSetters() {
        Supplier supplier = new Supplier();
 
        // Set values
        supplier.setId(1L);
        supplier.setName("Supplier A");
        supplier.setRut("12345678-9");
        supplier.setAddress("123 Main St");
        supplier.setContact("contact@example.com");
 
        // Assert values
        assertEquals(1L, supplier.getId());
        assertEquals("Supplier A", supplier.getName());
        assertEquals("12345678-9", supplier.getRut());
        assertEquals("123 Main St", supplier.getAddress());
        assertEquals("contact@example.com", supplier.getContact());
    }
}
