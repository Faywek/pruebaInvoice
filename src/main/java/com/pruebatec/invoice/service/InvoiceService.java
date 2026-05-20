package com.pruebatec.invoice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.dto.InvoiceDetailDTO;
import com.pruebatec.invoice.persistence.Invoice;
import com.pruebatec.invoice.persistence.InvoiceDetail;
import com.pruebatec.invoice.persistence.Supplier;
import com.pruebatec.invoice.repository.InvoiceRepository;
import com.pruebatec.invoice.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InvoiceService {
	
	private final static String MSG_INVOICE_NOT_FOUND	= "Factura no encontrada";
	
	@Autowired
    private InvoiceRepository invoiceRepository;
 
    @Autowired
    private SupplierRepository supplierRepository;
 
    public List<InvoiceDTO> findAll(InvoiceDTO invoiceDTO) {
    	
    	validateDate(invoiceDTO);
    	
        // Llamar al método del repositorio que usa Criteria API
        List<Invoice> invoices = invoiceRepository.findAllByCriteria(invoiceDTO);
        
        // Convertir las entidades a DTOs
        return invoices.stream()
                       .map(this::convertToDTO)
                       .collect(Collectors.toList());
    }
 
    public InvoiceDTO findById(Long id) {
        return invoiceRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }
 
    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        
    	validateInvoice(invoiceDTO); 
        
        Invoice invoice = convertToEntity(invoiceDTO);
        for (InvoiceDetail detail : invoice.getDetails()) {
            detail.setInvoice(invoice);
        }
        return convertToDTO(invoiceRepository.save(invoice));
    }
 
    public void softDelete(Long id) {
        Invoice invoice = validateNotDeleted( id );
        invoice.setDeleted(true);
        invoiceRepository.save(invoice);
    }
    
    public InvoiceDTO update(Long id, InvoiceDTO invoiceDTO)  {
    	Invoice invoice = validateNotDeleted( id );
    	
        if (invoiceDTO.getIssueDate() != null) {
            invoice.setIssueDate(invoiceDTO.getIssueDate());
        }
        if (invoiceDTO.getDueDate() != null) {
            invoice.setDueDate(invoiceDTO.getDueDate());
        }
        if (invoiceDTO.getFobValue() != null) {
            invoice.setFobValue(invoiceDTO.getFobValue());
        }
        if (invoiceDTO.getCurrencyType() != null) {
            invoice.setCurrencyType(invoiceDTO.getCurrencyType());
        }
        
        return convertToDTO(invoiceRepository.save(invoice));
    }
 
    private InvoiceDTO convertToDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setCurrencyType(invoice.getCurrencyType());
        dto.setIncoterm(invoice.getIncoterm());
        dto.setOriginCountry(invoice.getOriginCountry());
        dto.setDestinationCountry(invoice.getDestinationCountry());
        dto.setFobValue(invoice.getFobValue());
        dto.setSupplierId(invoice.getSupplier().getId());
        dto.setDetails(invoice.getDetails().stream().map(this::convertDetailToDTO).collect(Collectors.toList()));
        return dto;
    }
 
    private Invoice convertToEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getId());
        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setIssueDate(dto.getIssueDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setCurrencyType(dto.getCurrencyType());
        invoice.setIncoterm(dto.getIncoterm());
        invoice.setOriginCountry(dto.getOriginCountry());
        invoice.setDestinationCountry(dto.getDestinationCountry());
        invoice.setFobValue(dto.getFobValue());
        invoice.setCreationDate( LocalDateTime.now() );
        // Asociar proveedor
        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).orElse(null);
        invoice.setSupplier(supplier);
        return invoice;
    }
 
    private InvoiceDetailDTO convertDetailToDTO(InvoiceDetail detail) {
        InvoiceDetailDTO dto = new InvoiceDetailDTO();
        dto.setId(detail.getId());
        dto.setProductDescription(detail.getProductDescription());
        dto.setQuantity(detail.getQuantity());
        dto.setUnitPrice(detail.getUnitPrice());
        return dto;
    }
    
    private void validateInvoice(InvoiceDTO invoiceDTO) {
        // Validar que el proveedor exista
        if (invoiceDTO.getSupplierId() == null || !supplierRepository.existsById(invoiceDTO.getSupplierId())) {
            throw new IllegalArgumentException("El proveedor debe existir");
        }
     
        // Validar que la factura tenga al menos un detalle
        if (invoiceDTO.getDetails() == null || invoiceDTO.getDetails().isEmpty()) {
            throw new IllegalArgumentException("La factura debe tener al menos un detalle.");
        }
     
        // Validar detalles de la factura
        for (InvoiceDetailDTO detail : invoiceDTO.getDetails()) {
            if (detail.getQuantity() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser positiva");
            }
            if (detail.getUnitPrice() <= 0) {
                throw new IllegalArgumentException("El precio unitario debe ser positivo.");
            }
        }
     
    }
    
    private Invoice validateNotDeleted( long id) {
    	Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException( MSG_INVOICE_NOT_FOUND ));
        
    	if( invoice.getDeleted() )
    		throw new EntityNotFoundException ( "Factura no existe" );
    	
    	return invoice;
    }
    
    private void validateDate( InvoiceDTO invoiceDTO ) {
    	// Validar fechas
        if (invoiceDTO.getFechaDesde() != null && invoiceDTO.getFechaHasta() != null) {
            if (!invoiceDTO.getFechaDesde().isBefore(invoiceDTO.getFechaHasta())) {
                throw new IllegalArgumentException("La fecha de emisión debe ser anterior a la fecha de vencimiento.");
            }
        }
    }
}
