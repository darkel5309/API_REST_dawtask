CREATE TABLE tarea (
	id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT,
    fecha_creacion DATE DEFAULT CURRENT_DATE,
    fecha_vencimiento DATE,
    estado ENUM('PENDIENTE', 'EN_PROCESO', 'COMPLETADA') DEFAULT 'PENDIENTE'
);

INSERT INTO tarea (titulo, descripcion, fecha_vencimiento, estado) VALUES
	('Hacer la compra', 'Comprar patatas, pizzas y bebidas', '2024-05-17', 'COMPLETADA' ),
	('Hacer el proyecto de programación', 'Realizar una API Rest con endpoints', '2024-05-27', 'EN_PROCESO' ),
    ('Hacer el proyecto de base de datos', 'Realizar procedimientos, funciones y triggers', '2024-05-26', 'EN_PROCESO' ),
    ('Recoger la casa', 'Ordenar la habitación y limpiar la casa', '2024-05-25', 'PENDIENTE'),
    ('Estudiar sistemas', 'Estudiar para el exámen de sistemas del lunes 20', '2024-05-20', 'COMPLETADA' );