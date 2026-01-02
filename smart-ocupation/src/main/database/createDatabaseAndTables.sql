CREATE DATABASE SmartOcupationDB;
USE SmartOcupationDB;

CREATE TABLE Viviendas (
    id_vivienda INT AUTO_INCREMENT PRIMARY KEY,
    codigo_referencia VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100),
    metros INT,
    habitaciones INT,
    banos INT,
    precio_mensual DECIMAL(10, 2)
);

CREATE TABLE Alquileres (
    num_expediente INT AUTO_INCREMENT PRIMARY KEY,
    fecha_entrada DATE NOT NULL,
    tiempo_meses INT,
    nombre_cliente VARCHAR(100),
    dni_cliente VARCHAR(20),
    id_vivienda INT,
    FOREIGN KEY (id_vivienda) REFERENCES Viviendas(id_vivienda)
);

INSERT INTO Viviendas (codigo_referencia, ubicacion, metros, habitaciones, banos, precio_mensual) VALUES 
('REF-101', 'Calle Mayor 15', 90, 3, 2, 950.00),
('REF-102', 'Av. Libertad 3', 60, 2, 1, 700.00),
('REF-103', 'Plaza España 5', 120, 4, 3, 1200.00);

INSERT INTO Alquileres (fecha_entrada, tiempo_meses, nombre_cliente, dni_cliente, id_vivienda) VALUES 
('2023-01-10', 12, 'Carlos Ruiz', '11111111A', 1),
('2023-05-20', 6, 'Ana Gomez', '22222222B', 2),
('2024-02-15', 24, 'Luis Martinez', '33333333C', 3);