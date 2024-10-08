# Turnos Rotativos
## Descripción
Esta API fue desarrollada para gestionar y administrar las jornadas laborales de empleados. Permite registrar empleados y realizar validaciones de los datos ingresados (duplicación de documentos, emails, fechas de nacimiento incorrectas, entre otros). Además, la aplicación cuenta con una base de datos H2 en memoria y soporta requests a través de endpoints configurados en el servidor.

## Requisitos
JDK: Versión 17
Maven: Asegúrate de tener configurado Maven para la gestión de dependencias.
Postman: Para realizar pruebas a los endpoints de la API (el archivo .json con la colección de requests se incluye en la entrega)

## Tecnologías utilizadas
- Java 17
- Spring Boot
- H2 Database
- Postman

## Instalación
1. Clona el repositorio en tu máquina local. ( https://github.com/Ezzeorazi/turnos-rotativos.git )
2. Abre el proyecto en tu IDE de preferencia.
3. Ejecuta el proyecto.
4. Abre Postman y realiza las pruebas a los endpoints de la API.
5. En la carpeta original encontrara el json de postman.
6. ¡Listo!

## Endpoints
### Empleados
#### GET
http://localhost:8080/empleados

#### POST (Crear empleado)
http://localhost:8080/empleados/crear

#### PUT (Actualizar empleado por id)
http://localhost:8080/empleados/{id}

#### DELETE (Eliminar empleado por id)
http://localhost:8080/empleados/{id}

#### GET (Conceptos Laborales prepargados)
http://localhost:8080/empleados/concepto

### POST Jornadas crear
http://localhost:8080/jornada

#### GET Jornadas
http://localhost:8080/jornada

#### POST (Crear array de empleados)
http://localhost:8080/empleados/crear


## Modelos JSON
### Empleado
```
    {
    "nroDocumento": 12345678,
    "nombre": "Carlos",
    "apellido": "Lopez",
    "email": "carlos.lop1ez@example.com",
    "fechaNacimiento": "1995-07-30",
    "fechaIngreso": "2024-07-05"
    }

//Jornada

    {
    "idEmpleado": 1,
    "idConcepto": 2,
    "fecha": "2024-09-07",
    "hsTrabajadas": 10
    }
 
//Crear array de empleados

    [
  {
    "nroDocumento": 12345678,
    "nombre": "Carlos",
    "apellido": "Lopez",
    "email": "carlos.lop1ez@example.com",
    "fechaNacimiento": "1995-07-30",
    "fechaIngreso": "2024-07-05"
  },
  {
    "nroDocumento": 87654321,
    "nombre": "Ana",
    "apellido": "Garcia",
    "email": "ana.garcia2@example.com",
    "fechaNacimiento": "1990-03-14",
    "fechaIngreso": "2023-12-01"
  },
  {
    "nroDocumento": 23456789,
    "nombre": "Pedro",
    "apellido": "Martinez",
    "email": "pedro.martinez3@example.com",
    "fechaNacimiento": "1988-06-21",
    "fechaIngreso": "2022-11-15"
  },
  {
    "nroDocumento": 34567890,
    "nombre": "Lucia",
    "apellido": "Fernandez",
    "email": "lucia.fernandez4@example.com",
    "fechaNacimiento": "1993-02-05",
    "fechaIngreso": "2021-10-10"
  },
  {
    "nroDocumento": 45678901,
    "nombre": "Javier",
    "apellido": "Sanchez",
    "email": "javier.sanchez5@example.com",
    "fechaNacimiento": "1992-09-10",
    "fechaIngreso": "2024-05-22"
  }
]
```

