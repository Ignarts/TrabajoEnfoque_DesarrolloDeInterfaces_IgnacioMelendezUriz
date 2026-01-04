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
('2024-02-15', 24, 'Luis Martinez', '33333333C', 3),
('2020-03-01', 12, 'María López', '44444444D', 1),
('2019-07-15', 24, 'José Hernández', '55555555E', 2),
('2021-11-20', 6, 'Lucía Fernández', '66666666F', 3),
('2022-06-05', 36, 'Miguel García', '77777777G', 1),
('2020-12-12', 3, 'Sofía Martín', '88888888H', 2),
('2021-01-25', 18, 'Diego Torres', '99999999I', 3),
('2022-09-10', 12, 'Paula Ruiz', '10101010J', 1),
('2023-03-30', 6, 'Raúl Sánchez', '12121212K', 2),
('2024-07-01', 24, 'Carolina Díaz', '13131313L', 3),
('2020-05-22', 12, 'Fernando Molina', '14141414M', 1),
('2019-10-08', 6, 'Elena Ríos', '15151515N', 2),
('2021-08-18', 12, 'Andrés Castillo', '16161616O', 3),
('2022-02-02', 24, 'Natalia Vega', '17171717P', 1),
('2023-12-12', 12, 'Oscar Peña', '18181818Q', 2),
('2024-11-11', 6, 'Irene Morales', '19191919R', 3),
('2020-08-30', 36, 'Javier Ortega', '20202020S', 1),
('2021-04-14', 9, 'Marta Silva', '21212121T', 2),
('2022-10-20', 12, 'Álvaro Blanco', '22222223U', 3),
('2023-06-06', 24, 'Beatriz Cruz', '23232323V', 1),
('2019-03-03', 12, 'Santiago Navarro', '24242424W', 2),
('2020-11-11', 6, 'Rocío Palacios', '25252525X', 3),
('2021-09-09', 18, 'Tomás Gil', '26262626Y', 1),
('2022-04-04', 12, 'Luciana Rivas', '27272727Z', 2),
('2024-01-20', 24, 'Hugo Serrano', '28282828A', 3),
('2025-02-14', 12, 'Violeta Campos', '29292929B', 1),
('2020-02-29', 6, 'Pablo Luna', '30303030C', 2),
('2021-12-01', 12, 'Nuria Soler', '31313131D', 3);