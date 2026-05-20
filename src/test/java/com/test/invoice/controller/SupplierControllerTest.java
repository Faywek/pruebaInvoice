package com.test.invoice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pruebatec.invoice.controller.SupplierController;
import com.pruebatec.invoice.dto.SupplierDTO;
import com.pruebatec.invoice.service.SupplierService;

public class SupplierControllerTest {

	@InjectMocks
    private SupplierController supplierController;
 
    @Mock
    private SupplierService supplierService;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }
 
    @Test
    void testGetAllSuppliers() throws Exception {
        SupplierDTO supplier = new SupplierDTO();
        supplier.setId(1L);
        supplier.setName("Supplier A");
 
        when(supplierService.findAll()).thenReturn(Arrays.asList(supplier));
 
        List<SupplierDTO> suppliers = supplierController.getAllSuppliers();
        assertEquals(1, suppliers.size());
        assertEquals("Supplier A", suppliers.get(0).getName());
    }
 
    @Test
    void testGetSupplierById() throws Exception {
        SupplierDTO supplier = new SupplierDTO();
        supplier.setId(1L);
        supplier.setName("Supplier A");
 
        when(supplierService.findById(1L)).thenReturn(supplier);
 
        ResponseEntity<SupplierDTO> response = supplierController.getSupplierById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Supplier A", response.getBody().getName());
    }
 
    @Test
    void testCreateSupplier() throws Exception {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName("Supplier A");
 
        when(supplierService.save(any(SupplierDTO.class))).thenReturn(supplierDTO);
 
        ResponseEntity<SupplierDTO> response = supplierController.createSupplier(supplierDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Supplier A", response.getBody().getName());
    }
 
    @Test
    void testUpdateSupplier() throws Exception {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(1L);
        supplierDTO.setName("Updated Supplier");
 
        when(supplierService.save(any(SupplierDTO.class))).thenReturn(supplierDTO);
 
        ResponseEntity<SupplierDTO> response = supplierController.updateSupplier(1L, supplierDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Supplier", response.getBody().getName());
    }
 
    @Test
    void testDeleteSupplier() throws Exception {
        supplierController.deleteSupplier(1L);
        verify(supplierService, times(1)).delete(1L);
    }
}
