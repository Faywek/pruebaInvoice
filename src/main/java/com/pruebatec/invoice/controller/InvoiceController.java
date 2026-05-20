package com.pruebatec.invoice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
	
	@Autowired
    private InvoiceService invoiceService;
 
    @GetMapping
    public List<InvoiceDTO> getInvoices(
            @RequestParam String rutProveedor,
            @RequestParam(required = false) LocalDate fechaDesde,
            @RequestParam(required = false) LocalDate fechaHasta,
            @RequestParam(required = false) String numeroDocumento,
            @RequestParam(required = false) Double fob,
            @RequestParam(required = false) String moneda) {
        
    	InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setRutProveedor(rutProveedor);
        invoiceDTO.setFechaDesde(fechaDesde);
        invoiceDTO.setFechaHasta(fechaHasta);
        invoiceDTO.setInvoiceNumber(numeroDocumento);
        invoiceDTO.setFobValue(fob);
        invoiceDTO.setCurrencyType(moneda);
    	
        return invoiceService.findAll( invoiceDTO );
    }
 
    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.save(invoiceDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.softDelete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
        try {
            return ResponseEntity.ok(invoiceService.update(id, invoiceDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}




