package com.test.invoice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.pruebatec.invoice.controller.InvoiceController;
import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.service.InvoiceService;

public class InvoiceControllerTest {

	@InjectMocks
    private InvoiceController invoiceController;
 
    @Mock
    private InvoiceService invoiceService;
 
    private MockMvc mockMvc;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }
 
    @Test
    void testGetInvoices() throws Exception {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceNumber("INV-001");
        invoiceDTO.setRutProveedor("12345678-9");
 
        when(invoiceService.findAll(any())).thenReturn(Arrays.asList(invoiceDTO));
 
        List<InvoiceDTO> invoices = invoiceController.getInvoices("12345678-9", null, null, null, null, null);
        assertEquals(1, invoices.size());
        assertEquals("INV-001", invoices.get(0).getInvoiceNumber());
    }
 
    @Test
    void testCreateInvoice() throws Exception {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceNumber("INV-001");
 
        when(invoiceService.save(any(InvoiceDTO.class))).thenReturn(invoiceDTO);
 
        ResponseEntity<InvoiceDTO> response = invoiceController.createInvoice(invoiceDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("INV-001", response.getBody().getInvoiceNumber());
    }
 
    @Test
    void testDeleteInvoice() throws Exception {
        doNothing().when(invoiceService).softDelete(1L);
        ResponseEntity<Void> response = invoiceController.deleteInvoice(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(invoiceService, times(1)).softDelete(1L);
    }
 
    @Test
    void testUpdateInvoice() throws Exception {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceNumber("Updated Invoice");
 
        when(invoiceService.update(any(Long.class), any(InvoiceDTO.class))).thenReturn(invoiceDTO);
 
        ResponseEntity<InvoiceDTO> response = invoiceController.updateInvoice(1L, invoiceDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Invoice", response.getBody().getInvoiceNumber());
    }
}
