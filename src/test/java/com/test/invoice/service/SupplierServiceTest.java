package com.test.invoice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pruebatec.invoice.dto.SupplierDTO;
import com.pruebatec.invoice.persistence.Supplier;
import com.pruebatec.invoice.repository.SupplierRepository;
import com.pruebatec.invoice.service.SupplierService;

public class SupplierServiceTest {

	@InjectMocks
    private SupplierService supplierService;
 
    @Mock
    private SupplierRepository supplierRepository;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testFindAll() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Supplier A");
        
        when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier));
 
        List<SupplierDTO> suppliers = supplierService.findAll();
        assertEquals(1, suppliers.size());
        assertEquals("Supplier A", suppliers.get(0).getName());
    }
 
    @Test
    void testFindById() {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Supplier A");
        
        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
 
        SupplierDTO found = supplierService.findById(1L);
        assertEquals("Supplier A", found.getName());
    }
 
    @Test
    void testSave() {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setName("Supplier A");
 
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("Supplier A");
 
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);
 
        SupplierDTO saved = supplierService.save(supplierDTO);
        assertEquals("Supplier A", saved.getName());
    }
 
    @Test
    void testDelete() {
        supplierService.delete(1L);
        verify(supplierRepository, times(1)).deleteById(1L);
    }
}
