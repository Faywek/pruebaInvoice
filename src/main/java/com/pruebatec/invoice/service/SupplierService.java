package com.pruebatec.invoice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatec.invoice.dto.SupplierDTO;
import com.pruebatec.invoice.persistence.Supplier;
import com.pruebatec.invoice.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
    private SupplierRepository supplierRepository;
 
    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
 
    public SupplierDTO findById(Long id) {
        return supplierRepository.findById(id)
            .map(this::convertToDTO)
            .orElse(null);
    }
 
    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplier = convertToEntity(supplierDTO);
        return convertToDTO(supplierRepository.save(supplier));
    }
 
    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
 
    private SupplierDTO convertToDTO(Supplier supplier) {
    	SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setRut(supplier.getRut());
        dto.setAddress(supplier.getAddress());
        dto.setContact(supplier.getContact());
        return dto;
    }
 
    private Supplier convertToEntity(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setRut(dto.getRut());
        supplier.setAddress(dto.getAddress());
        supplier.setContact(dto.getContact());
        return supplier;
    }
}
