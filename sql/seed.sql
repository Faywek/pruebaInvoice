-- Insertar Proveedores
INSERT INTO supplier (name, rut, address, contact) VALUES 
('Proveedor A', '12345678-9', 'Calle Falsa 123', 'Contacto A'),
('Proveedor B', '98765432-1', 'Avenida Siempre Viva 456', 'Contacto B'),
('Proveedor C', '11223344-5', 'Boulevard de los Sueños Rotos 789', 'Contacto C');

-- Insertar Facturas
INSERT INTO invoice (invoice_number, issue_date, due_date, currency_type, incoterm, origin_country, destination_country, fob_value, supplier_id) VALUES 
('INV-001', '2023-01-15', '2023-02-15', 'USD', 'FOB', 'USA', 'Chile', 1000.00, 1),
('INV-002', '2023-02-20', '2023-03-20', 'USD', 'CIF', 'Canada', 'Chile', 1500.00, 1),
('INV-003', '2023-03-10', '2023-04-10', 'CLP', 'CIF', 'Chile', 'Argentina', 2000.00, 2),
('INV-004', '2023-04-05', '2023-05-05', 'CLP', 'FOB', 'Chile', 'Perú', 1200.00, 2),
('INV-005', '2023-05-01', '2023-06-01', 'USD', 'CIF', 'Chile', 'Brasil', 1800.00, 3);

-- Insertar Detalles de Factura
INSERT INTO invoice_detail (product_description, quantity, unit_price, invoice_id) VALUES 
('Producto A1', 10, 100.00, 1),
('Producto A2', 5, 150.00, 1),
('Producto B1', 8, 200.00, 2),
('Producto C1', 15, 90.00, 3),
('Producto C2', 12, 150.00, 3),
('Producto D1', 7, 170.00, 4),
('Producto E1', 20, 80.00, 5),
('Producto E2', 10, 120.00, 5);