package com.test.invoice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.dto.InvoiceDetailDTO;
import com.pruebatec.invoice.persistence.Invoice;
import com.pruebatec.invoice.persistence.Supplier;
import com.pruebatec.invoice.repository.InvoiceRepository;
import com.pruebatec.invoice.repository.SupplierRepository;
import com.pruebatec.invoice.service.InvoiceService;

public class InvoiceServiceTest {

	@Mock
    private InvoiceRepository invoiceRepository;
 
    @Mock
    private SupplierRepository supplierRepository;
 
    @InjectMocks
    private InvoiceService invoiceService;
 
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    public void testFindAll() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        
        // Crea un detalle de factura para evitar la excepción
        InvoiceDetailDTO detailDTO = new InvoiceDetailDTO();
        detailDTO.setQuantity(1);
        detailDTO.setUnitPrice(100.0);
        detailDTO.setProductDescription("Test Product");
        
        List<InvoiceDetailDTO> details = new ArrayList<>();
        details.add(detailDTO);
        invoiceDTO.setDetails(details);
        
        // Simular un proveedor
        Supplier supplier = new Supplier();
        supplier.setId(1L); // Establecer un ID para el proveedor

        // Crea una lista de facturas
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setSupplier(supplier); // Asegúrate de que la factura tenga un proveedor
        invoiceList.add(invoice);

        // Configurar el mock del repositorio
        when(invoiceRepository.findAllByCriteria(invoiceDTO)).thenReturn(invoiceList);

        // Act
        List<InvoiceDTO> result = invoiceService.findAll(invoiceDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(invoiceRepository).findAllByCriteria(invoiceDTO);
    }
 
    @Test
    public void testFindById_ExistingInvoice() {
    	
    	// Simular un proveedor
        Supplier supplier = new Supplier();
        supplier.setId(1L); // Establecer un ID para el proveedor
    	
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setSupplier(supplier);
 
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
 
        // Act
        InvoiceDTO result = invoiceService.findById(invoiceId);
 
        // Assert
        assertNotNull(result);
        assertEquals(invoiceId, result.getId());
        verify(invoiceRepository).findById(invoiceId);
    }
 
    @Test
    public void testFindById_NonExistingInvoice() {
        // Arrange
        Long invoiceId = 1L;
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());
 
        // Act
        InvoiceDTO result = invoiceService.findById(invoiceId);
 
        // Assert
        assertNull(result);
        verify(invoiceRepository).findById(invoiceId);
    }
 
    @Test
    public void testSave_ValidInvoice() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setSupplierId(1L); // Asignar un ID de proveedor que existirá en el mock
        invoiceDTO.setIssueDate(LocalDate.now());
        
        // Crear un detalle de factura
        InvoiceDetailDTO detailDTO = new InvoiceDetailDTO();
        detailDTO.setQuantity(1);
        detailDTO.setUnitPrice(100.0);
        detailDTO.setProductDescription("Test Product");

        List<InvoiceDetailDTO> details = new ArrayList<>();
        details.add(detailDTO);
        invoiceDTO.setDetails(details); // Asignar detalles al InvoiceDTO

        // Simular el repositorio de proveedores
        when(supplierRepository.existsById(invoiceDTO.getSupplierId())).thenReturn(true);

        // Simular el repositorio de proveedores para la conversión
        Supplier supplier = new Supplier();
        supplier.setId(1L); // Establecer un ID para el proveedor
        when(supplierRepository.findById(invoiceDTO.getSupplierId())).thenReturn(Optional.of(supplier));

        // Simular el repositorio de facturas
        Invoice invoice = new Invoice();
        invoice.setSupplier(supplier);
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        // Act
        InvoiceDTO result = invoiceService.save(invoiceDTO);

        // Assert
        assertNotNull(result);
        verify(invoiceRepository).save(any(Invoice.class));
    }
 
    @Test
    public void testSave_InvalidInvoice() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setSupplierId(1L); // Suponiendo que este ID no existe
        invoiceDTO.setDetails(new ArrayList<>());
 
        when(supplierRepository.existsById(invoiceDTO.getSupplierId())).thenReturn(false);
 
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> invoiceService.save(invoiceDTO));
    }
 
    @Test
    public void testSoftDelete_ExistingInvoice() {
        // Arrange
        Long invoiceId = 1L;
        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
 
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
 
        // Act
        invoiceService.softDelete(invoiceId);
 
        // Assert
        assertTrue(invoice.getDeleted()); // Asegúrate de que la propiedad se haya cambiado
        verify(invoiceRepository).save(invoice);
    }
 
    @Test
    public void testSoftDelete_NonExistingInvoice() {
        // Arrange
        Long invoiceId = 1L;
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> invoiceService.softDelete(invoiceId));
    }
 
    @Test
    public void testUpdate_ExistingInvoice() {
    	
    	Supplier supplier = new Supplier();
        supplier.setId(1L); // Establecer un ID para el proveedor
    	
        // Arrange
        Long id = 1L;
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setIssueDate(LocalDate.now());
        
        Invoice invoice = new Invoice();
        invoice.setId(id);
        invoice.setSupplier(supplier);
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
 
        // Act
        InvoiceDTO result = invoiceService.update(id, invoiceDTO);
 
        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(invoiceRepository).save(invoice);
    }
 
    @Test
    public void testUpdate_NonExistingInvoice() {
        // Arrange
        Long id = 1L;
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        when(invoiceRepository.findById(id)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> invoiceService.update(id, invoiceDTO));
    }
}
