# TKOH Tickets API

Prueba Técnica Backend - Studios TKOH  
Candidato: Aldo Malpica

---

## Descripción

TKOH Tickets es una API RESTful desarrollada con Spring Boot para la gestión de reservas de entradas para eventos como conciertos y obras de teatro.

El objetivo principal no es construir un CRUD básico, sino simular un sistema real capaz de manejar múltiples usuarios concurrentes, garantizando la integridad de los datos y evitando la sobreventa de tickets.

---

## Objetivo

Desarrollar una API segura, escalable y consistente que permita:

- Registro y autenticación de usuarios
- Gestión de eventos
- Reserva de entradas
- Manejo correcto de concurrencia
- Control de acceso mediante roles

---

## Tecnologías utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL
- Spring Security
- JWT (JSON Web Token)
- Lombok
- Maven

---

## Estructura del proyecto
com.tkoh

config Configuración de seguridad
controller Endpoints REST
dto Objetos de transferencia de datos
entity Entidades JPA
exception Manejo global de errores
mapper Conversión entre entidades y DTOs
repository Acceso a datos
security Configuración JWT y filtros
service Lógica de negocio
util Clases auxiliares

---

## Autenticación

La API utiliza autenticación basada en JWT.

### Registro de usuario

POST /api/auth/register

Body:
{
    "_comentario": "Este es un registro de usuario",
    "username": "Meliodas",
    "password": "tupapi123k",
    "email": "Meliodas24@gmail.com"
}


---

### Login

POST /api/auth/login

Body:
{
    "username": "Meliodas",
    "password": "tupapi123k"
}

{
"token": "jwt_token",
"type": "Bearer"
}


---

## Endpoints principales

### Eventos

Obtener lista de eventos (público)

GET /api/events?page=0&size=10

---

Crear evento (solo ADMIN)

POST /api/events  
Header:
Authorization: Bearer TOKEN

---

### Reservas

Crear reserva (solo USER)

POST /api/reservations  
Header:
Authorization: Bearer TOKEN

Body:
{
"eventId": 1,
"quantity": 2
}


---

## Manejo de concurrencia

Para evitar la sobreventa de tickets se utilizó bloqueo optimista mediante la anotación @Version en la entidad Event.

Esto permite que, cuando múltiples usuarios intentan reservar al mismo tiempo, solo las transacciones válidas se completen, evitando inconsistencias en el número de tickets disponibles.

---

## Transacciones

El proceso de reserva se ejecuta dentro de una transacción utilizando @Transactional.

Esto asegura que las siguientes operaciones se comporten como una sola unidad:

1. Descontar tickets disponibles
2. Registrar la reserva
3. Simular el proceso de confirmación

Si alguna falla, todas las operaciones se revierten automáticamente.

---

## Manejo de errores

Se implementó un manejo global de excepciones mediante @RestControllerAdvice.

Las respuestas de error siguen un formato consistente:

{
"error": "Not enough tickets",
"code": 400
}


Esto evita exponer información interna del servidor.

---

## Arquitectura

El proyecto sigue una separación clara de responsabilidades:

- Controller: Maneja las solicitudes HTTP
- Service: Contiene la lógica de negocio
- Repository: Acceso a base de datos
- DTO: Transferencia de datos
- Mapper: Conversión entre entidades y DTOs

Las entidades nunca se exponen directamente en las respuestas.

---

## Configuración

Archivo application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/tkoh_tickets
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update

jwt.secret=secretkey123456
jwt.expiration=86400000


---

## Ejecución del proyecto

1. Clonar el repositorio

git clone https://github.com/tuusuario/tkoh-tickets.git

2. Ingresar al proyecto

cd tkoh-tickets

3. Ejecutar la aplicación

mvn spring-boot:run

---

## Pruebas

El proyecto puede probarse mediante Postman utilizando los endpoints definidos.  
Se recomienda probar escenarios de concurrencia para validar que no exista sobreventa.

---

## Conclusión

Este proyecto demuestra la implementación de una API backend orientada a escenarios reales, abordando problemas de concurrencia, seguridad y arquitectura limpia.

No se trata únicamente de que funcione, sino de que funcione correctamente bajo condiciones exigentes.
