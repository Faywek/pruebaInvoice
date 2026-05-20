package com.test.invoice.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pruebatec.invoice.dto.InvoiceDTO;
import com.pruebatec.invoice.persistence.Invoice;
import com.pruebatec.invoice.persistence.Supplier;
import com.pruebatec.invoice.repository.impl.InvoiceCustomRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class InvoiceCustomRepositoryImplTest {

	private EntityManager entityManager;
    private InvoiceCustomRepositoryImpl invoiceRepository;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<Invoice> criteriaQuery;
    private Root<Invoice> root;
    private TypedQuery<Invoice> typedQuery;
 
    @BeforeEach
    public void setUp() {
        // Crear mocks
        entityManager = mock(EntityManager.class);
        invoiceRepository = new InvoiceCustomRepositoryImpl();
        invoiceRepository.setEntityManager(entityManager); // Asegúrate de tener un método setEntityManager
 
        criteriaBuilder = mock(CriteriaBuilder.class);
        criteriaQuery = mock(CriteriaQuery.class);
        root = mock(Root.class);
        typedQuery = mock(TypedQuery.class);
 
        // Configuración de mocks
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Invoice.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Invoice.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
    }
 
    @Test
    public void testFindAllByCriteria_WithAllFilters() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setRutProveedor("12345678-9");
        invoiceDTO.setFechaDesde(LocalDate.of(2023, 1, 1));
        invoiceDTO.setFechaHasta(LocalDate.of(2023, 12, 31));
        invoiceDTO.setInvoiceNumber("INV-001");
        invoiceDTO.setFobValue(1000.0d);
        invoiceDTO.setCurrencyType("USD");
 
        List<Invoice> invoiceList = new ArrayList<>();
 
        Supplier sup = new Supplier();
        sup.setId(1L);
        sup.setRut("12345678-9");
 
        Invoice inv = new Invoice();
        inv.setSupplier(sup);
        inv.setInvoiceNumber("INV-001");
        inv.setFobValue(1000.0d);
        inv.setCurrencyType("USD");
        inv.setIssueDate(LocalDate.of(2023, 6, 15)); // Fecha dentro del rango
        invoiceList.add(inv);
 
        // Mocks de Predicate
        Predicate predicate1 = mock(Predicate.class);
        Predicate predicate2 = mock(Predicate.class);
        Predicate predicate3 = mock(Predicate.class);
        Predicate predicate4 = mock(Predicate.class);
        Predicate predicate5 = mock(Predicate.class);
        Predicate predicate6 = mock(Predicate.class);
 
        // Configuración de condiciones
        when(root.get("supplier")).thenReturn(mock(Root.class)); // Simulando la relación
        when(root.get("supplier").get("rut")).thenReturn(mock(Path.class)); // Simulando el acceso al RUT
        when(criteriaBuilder.equal(root.get("supplier").get("rut"), invoiceDTO.getRutProveedor())).thenReturn(predicate1);
        when(criteriaBuilder.between(root.get("issueDate"), invoiceDTO.getFechaDesde(), invoiceDTO.getFechaHasta())).thenReturn(predicate2);
        when(criteriaBuilder.equal(root.get("invoiceNumber"), invoiceDTO.getInvoiceNumber())).thenReturn(predicate3);
        when(criteriaBuilder.equal(root.get("fobValue"), invoiceDTO.getFobValue())).thenReturn(predicate4);
        when(criteriaBuilder.equal(root.get("currencyType"), invoiceDTO.getCurrencyType())).thenReturn(predicate5);
        when(criteriaBuilder.equal(root.get("deleted"), false)).thenReturn(predicate6);
 
        // Mocking select() y where()
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate[].class))).thenReturn(criteriaQuery);
 
        // Configurar el resultado de la consulta
        when(typedQuery.getResultList()).thenReturn(invoiceList);
 
        // Act
        List<Invoice> result = invoiceRepository.findAllByCriteria(invoiceDTO);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(criteriaQuery).select(root);
        verify(criteriaQuery).where(any(Predicate[].class)); // Verifica que se llame a where con los predicates
    }
 
    @Test
    public void testFindAllByCriteria_WithoutFilters() {
        // Arrange
        InvoiceDTO invoiceDTO = new InvoiceDTO(); // Sin filtros
 
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(new Invoice());
 
        // Mocking select() y where()
        when(criteriaQuery.select(root)).thenReturn(criteriaQuery);
        when(criteriaQuery.where(any(Predicate[].class))).thenReturn(criteriaQuery);
 
        // Configurar el resultado de la consulta
        when(typedQuery.getResultList()).thenReturn(invoiceList);
 
        // Act
        List<Invoice> result = invoiceRepository.findAllByCriteria(invoiceDTO);
 
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(criteriaQuery).select(root);
        verify(criteriaQuery).where(any(Predicate[].class)); // Verifica que se llame a where
    }
}
