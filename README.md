# Sistema de Gestión para Clínica Veterinaria

### 🏁 **Contexto General**

Margarita está a punto de abrir su clínica veterinaria y necesita un sistema para gestionar los datos de sus pacientes y sus tutores. Este sistema será esencial para mantener organizada toda la información de los animales atendidos y sus responsables.

En este primer sprint, nos enfocaremos en las funcionalidades más importantes del sistema: el **gestor de pacientes** y el **gestor de tutores**. Margarita quiere empezar con un sistema sencillo pero funcional, que permita registrar, buscar, listar, actualizar y eliminar tanto pacientes como tutores.

El sistema será implementado como una **API REST** desarrollada en **Java Spring Boot**, y los datos serán almacenados en una base de datos H2.

---

## 👀 **Objetivo del Primer Sprint**

En este primer sprint, se desarrollará lo siguiente:

1. **Gestor de Tutores:**
    - Registro, búsqueda, listado, actualización y eliminación de tutores.
    - Relación entre tutores y pacientes, permitiendo reutilizar los datos de los tutores para varios pacientes.
2. **Gestor de Pacientes:**
    - Registro, búsqueda, listado, actualización y eliminación de pacientes.
    - Asociación de pacientes con sus respectivos tutores.

Con esto, Margarita podrá registrar y gestionar toda la información de sus pacientes y sus tutores de manera centralizada.

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

**Contexto:**

Además de los pacientes, Margarita necesita poder registrar a los tutores, quienes son responsables de traer a los animales a la clínica. Este módulo permitirá gestionar la información de los tutores de manera centralizada, asegurando que varios pacientes puedan asociarse al mismo tutor.

**Requisitos Funcionales:**

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
    - Permitir modificar los datos de un tutor registrado, como su nombre o teléfono.
5. **Eliminación de Tutores:**
    - Permitir eliminar un tutor del sistema si ya no tiene pacientes asociados.

---

## 😿 **Gestor de Pacientes**

**Contexto:**

Margarita necesita poder registrar y gestionar a los pacientes de su clínica. Cada paciente debe tener información básica como su nombre, edad, especie, además de estar asociado a un tutor que sea responsable de él. Este módulo permitirá mantener un registro organizado de todos los pacientes y sus datos.

**Requisitos Funcionales:**

1. **Registro de Pacientes:**
    - Permitir registrar pacientes con la siguiente información:
        - Nombre del paciente.
        - Especie (**perro** o **gato**).
        - Raza (opcional y por defecto: “unknown”).
        - Edad.
        - Número de identificación único (autogenerado).
        - Tutor asociado (por ID).
2. **Búsqueda de Pacientes:**
    - Buscar pacientes por su número de identificación único.
    - Ver todos los detalles del paciente, incluyendo el tutor asociado.
3. **Listado de Pacientes:**
    - Listar todos los pacientes registrados en el sistema.
    - Mostrar información básica como nombre, especie, y tutor asociado.
4. **Actualización de Pacientes:**
    - Permitir modificar los datos de un paciente registrado, como su nombre, especie, edad, tutor, etc.
5. **Eliminación de Pacientes:**
    - Permitir eliminar un paciente del sistema, asegurando que ya no esté disponible en los listados o búsquedas.

---

## **Resumen del Alcance del Primer Sprint**

| **Módulo**           | **Funcionalidades**                                                                                                                                 |
|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **Gestor de Pacientes** | Registrar pacientes, buscar por identificación, listar todos los pacientes, actualizar y eliminar.                                                |
| **Gestor de Tutores**   | Registrar tutores, buscar por nombre o identificación, listar todos los tutores, actualizar y eliminar.                                           |

---

## **Diagrama UML**

![Captura de pantalla 2024-11-28 102249](https://github.com/user-attachments/assets/1185712d-4051-47c3-92e0-2bdc9be526e0)

https://lucid.app/lucidchart/9609464a-7333-4dbf-a6cb-40a017e96fa2/edit?invitationId=inv_9be1c35e-06d5-42d8-bb90-6c8deadbb393&page=0_0#


Este esquema representa el diseño del sistema de gestión para una clínica veterinaria, mostrando los controladores, repositorios y entidades principales:

Controladores:

- PatientController: Gestiona las operaciones CRUD (crear, leer, actualizar, eliminar) para pacientes.
- MentorController: Maneja las operaciones CRUD para tutores (mentores).
  
Repositorios:

- PatientRepository: Interface para acceder a la base de datos de pacientes.
- MentorRepository: Interface que extiende JpaRepository, con métodos adicionales como findByName.

Entidades:

- Patient: Representa a los pacientes con atributos como ID, nombre, especie, raza, edad y un vínculo con su tutor.
- Mentor: Representa a los tutores, con atributos como ID, nombre, apellido, teléfono y una lista de pacientes asociados.

Este diseño organiza las relaciones entre pacientes y tutores, con funcionalidades clave para mantener el sistema.

---

## **Esquema E/R**

![Captura de pantalla 2024-11-28 103814](https://github.com/user-attachments/assets/5d6ec824-7f75-41a9-b88d-8679522478d2)

Uno a Muchos (1:N):

- Cada tutor (Menthor) puede estar asociado con múltiples pacientes (Patients), pero cada paciente tiene un único tutor. Esto se implementa mediante la llave foránea fk_id_Menthor en la tabla Patients, que referencia el Id en la tabla Menthor.

---

## **Planificación**

- Trello https://trello.com/invite/b/673c9f6e1dbab5ef51910ebf/ATTI7adb4466e1d431c6b699b7cfcee1e142C3D560B0/clinicaveterinaria

---

## **Integrantes**

- Paloma Babot
- Fran García
- Layla Gómez
- Iván López

---

## **Resultados Esperados**

1. Margarita podrá gestionar la información de los tutores y pacientes desde un sistema centralizado.
2. Cada paciente estará asociado a un tutor existente, garantizando la consistencia de los datos.
3. El sistema estará preparado para ser extendido en el siguiente sprint, donde se incluirá la gestión de citas.

---






