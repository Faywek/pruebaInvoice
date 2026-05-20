-- Crear una base de datos
CREATE DATABASE IF NOT EXISTS testbdd;
 
-- Usar la base de datos
USE testbdd;
 
-- Otorgar privilegios al usuario
GRANT ALL PRIVILEGES ON testbdd.* TO 'test'@'%';

FLUSH PRIVILEGES;

CREATE TABLE supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    rut VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255),
    contact VARCHAR(100)
);

CREATE TABLE invoice (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    currency_type VARCHAR(10),
    incoterm VARCHAR(10),
    origin_country VARCHAR(100),
    destination_country VARCHAR(100),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fob_value FLOAT ,
    deleted BOOLEAN DEFAULT FALSE,
    supplier_id BIGINT,
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

CREATE TABLE invoice_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    unit_price FLOAT  NOT NULL,
    amount FLOAT  AS (quantity * unit_price) STORED,
    invoice_id BIGINT,
    FOREIGN KEY (invoice_id) REFERENCES invoice(id)
);