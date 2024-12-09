# Sistema de Gestión para Clínica Veterinaria

### 🏁 **Contexto General**

Margarita está a punto de abrir su clínica veterinaria y necesita un sistema para gestionar los datos de sus pacientes, tutores y citas. Este sistema será esencial para mantener organizada toda la información de los animales atendidos, sus responsables y el historial de citas.

En este sprint, nos enfocamos en desarrollar tres módulos principales: el **gestor de pacientes**, el **gestor de tutores** y el **gestor de citas**, con funcionalidades clave para garantizar un sistema funcional y escalable.

El sistema ha sido desarrollado como una **API REST** utilizando **Java Spring Boot**, y los datos se almacenan en una base de datos H2.

---

## 👀 **Objetivo del Sprint**

En este sprint, hemos desarrollado el módulo Appointments.

**Gestor de Citas (Appointments):**
    - Registro, búsqueda, listado, actualización y eliminación de citas.
    - Asociación de citas con pacientes registrados en el sistema.

Con esto, Margarita podrá gestionar toda la información de sus pacientes, tutores y el historial de citas en su clínica.

---

## **Tecnologías Utilizadas**

El sistema ha sido desarrollado utilizando las siguientes tecnologías:

- **Java 21**: Lenguaje de programación principal.
- **Spring Boot 3.4.0**: Framework para el desarrollo del backend.
- **H2 Database**: Base de datos en memoria para pruebas y almacenamiento local.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **Postman**: Herramienta para pruebas de la API REST.
- **Git**: Sistema de control de versiones para la colaboración y gestión del código fuente.

---

## 🧑🏽‍🦰 **Gestor de Tutores**

### **Requisitos Funcionales:**

1. **Registro de Tutores:**
    - Permitir registrar tutores con la siguiente información:
        - Nombre completo (nombre y apellidos).
        - Número de teléfono.
        - Identificación única del tutor (autogenerada por el sistema).
2. **Búsqueda de Tutores:**
    - Buscar tutores por su nombre o número de identificación único.
    - Ver todos los detalles del tutor, incluyendo la lista de pacientes que están bajo su cuidado.
3. **Listado de Tutores:**
    - Listar todos los tutores registrados en el sistema.
    - Mostrar información básica como nombre y teléfono.
4. **Actualización de Tutores:**
    - Permitir modificar los datos de un tutor registrado.
5. **Eliminación de Tutores:**
    - Permitir eliminar un tutor del sistema si ya no tiene pacientes asociados.

---

## 😿 **Gestor de Pacientes**

### **Requisitos Funcionales:**

1. **Registro de Pacientes:**
    - Permitir registrar pacientes con la siguiente información:
        - Nombre del paciente.
        - Especie (**perro** o **gato**).
        - Raza (opcional, con valor predeterminado: “unknown”).
        - Edad.
        - Número de identificación único (autogenerado).
        - Tutor asociado (por ID).
2. **Búsqueda de Pacientes:**
    - Buscar pacientes por su número de identificación único.
    - Ver todos los detalles del paciente, incluyendo el tutor asociado.
3. **Listado de Pacientes:**
    - Listar todos los pacientes registrados en el sistema.
    - Mostrar información básica como nombre, especie y tutor asociado.
4. **Actualización de Pacientes:**
    - Permitir modificar los datos de un paciente registrado.
5. **Eliminación de Pacientes:**
    - Permitir eliminar un paciente del sistema, asegurando que ya no esté disponible en los listados o búsquedas.

---

## 📅 **Gestor de Citas**

### **Requisitos Funcionales:**

1. **Registro de Citas:**
    - Permitir registrar citas con la siguiente información:
        - Fecha.
        - Hora.
        - Motivo de la consulta.
        - Paciente asociado (por ID).
2. **Búsqueda de Citas:**
    - Buscar citas por su ID único.
    - Ver todos los detalles de la cita, incluyendo el paciente asociado.
3. **Listado de Citas:**
    - Listar todas las citas registradas en el sistema.
    - Mostrar información básica como fecha, hora, motivo y paciente asociado.
4. **Actualización de Citas:**
    - Permitir modificar los datos de una cita registrada.
5. **Eliminación de Citas:**
    - Permitir eliminar una cita del sistema.

---

## **Resumen del Alcance del Sprint**

| **Módulo**           | **Funcionalidades**                                                                                                                                 |
|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Gestor de Pacientes** | Registrar pacientes, buscar por identificación, listar todos los pacientes, actualizar y eliminar.                                                |
| **Gestor de Tutores**   | Registrar tutores, buscar por nombre o identificación, listar todos los tutores, actualizar y eliminar.                                           |
| **Gestor de Citas**     | Registrar citas, buscar por identificación, listar todas las citas, actualizar y eliminar.                                                        |

---

## **Diagrama UML**

> **Actualización pendiente para incluir la entidad y relación de `Appointments`.**

---

## **Planificación**

- [Trello](https://trello.com/invite/b/673c9f6e1dbab5ef51910ebf/ATTI7adb4466e1d431c6b699b7cfcee1e142C3D560B0/clinicaveterinaria)

---

## **Integrantes**

- Paloma Babot
- Fran García
- Layla Gómez
- Iván López
