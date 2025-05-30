DROP DATABASE IF EXISTS gestioncurso;
CREATE DATABASE gestioncurso CHARACTER SET utf8mb4;
USE gestioncurso;

CREATE TABLE Rol (
    id BIGINT PRIMARY KEY,
    nombre_rol VARCHAR(200) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

CREATE TABLE Usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    id_rol BIGINT,
    FOREIGN KEY(id_rol) REFERENCES Rol(id) ON DELETE RESTRICT
);

CREATE TABLE Curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_profesor BIGINT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    numero_horas INTEGER NOT NULL,
    habilitado BOOLEAN NOT NULL DEFAULT TRUE,
    privado BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY(id_profesor) REFERENCES Usuario(id) ON DELETE RESTRICT
);

CREATE TABLE Tarea (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_curso BIGINT,
    nombre VARCHAR(100),
    descripcion VARCHAR(500),
    fecha_limite DATE,
    archivo VARCHAR(255),
    FOREIGN KEY (id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

CREATE TABLE Curso_Usuario (
    id_usuario BIGINT NOT NULL,
    id_curso BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY(id_curso) REFERENCES Curso(id) ON DELETE CASCADE
);

INSERT INTO Rol (id, nombre_rol, descripcion) VALUES
(0, 'ADMIN', 'Usuario con todos los permisos'),
(1, 'ALUMNO', 'Usuario normal'),
(2, 'PROFESOR', 'Profesor');

INSERT INTO Usuario (id, nombre, email, password, id_rol) VALUES
(1, 'Jesus', 'admin@gmail.com', '1234', 0),
(2, 'Pablo', 'alumno1@example.com', '1234', 1),
(3, 'Roberta', 'alumno2@example.com', '1234', 1),
(4, 'Cristina', 'alumno3@example.com', '1234', 1),
(5, 'Paola', 'alumno4@example.com', '1234', 1),
(6, 'Paula', 'profesor1@example.com', '1234', 2);

INSERT INTO Curso (id, id_profesor, nombre, descripcion, numero_horas, habilitado, privado) VALUES
(1, 6, 'Curso de Angular', 'Curso de introducción a Angular', 200, true, false),
(2, 6, 'Curso de React', 'Curso de introducción a React', 60, true, false),
(3, 6, 'Curso de Vue.js', 'Curso de introducción a Vue.js', 100, false, false);
