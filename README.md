# ForoHub API REST  

Este proyecto es una API REST desarrollada con **Spring Boot**, diseñada para gestionar un foro en línea llamado **ForoHub**. La API incluye funcionalidades de creación, consulta de publicaciones y temas, además de autenticación y autorización mediante **JWT (JSON Web Tokens)** para garantizar la seguridad de las operaciones.

## Tecnologias
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-brightgreen)
![Docker](https://img.shields.io/badge/Docker-24.0.0-blue)
![Java](https://img.shields.io/badge/Java-23-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0.33-blue)


## 📑 Características principales  

- **Gestión de publicaciones**: Crear y consultar publicaciones del foro.
- **Gestión de temas**: Obtener la lista de temas disponibles.
- **Autenticación y autorización**: Acceso protegido con autenticación JWT.
- **Cumplimiento de seguridad**: Roles y permisos para proteger los endpoints.

---

## 🚀 Endpoints de la API  

### **`/api/login`**  

#### **[POST] Login**  
Permite a un usuario autenticarse en la app.  

- **URL**: `/api/login`  
- **Request Body (JSON)**:  
  ```json
  {
      "username": "username",
      "password": "password"
  }
### **`/api/topics`** 
#### **[POST] Crear una topico**  
Permite a un usuario autenticado crear una nueva topico en el foro.  

- **URL**: `/api/topics`  
- **Autorización**: Requiere autenticación JWT.  
- **Request Body (JSON)**:  
  ```json
  {
    "title": "topic title",
    "message": "a simple message",
    "author": "author of the topic",
    "course": "class, category, type"
  }

#### **[PUT, DELETE, GET] topic**  
- **URL**: `/api/topics/id`  
- **Autorización**: Requiere autenticación JWT.  
- **Request Body (JSON)**:  
### **`/api/posts`**  

#### **[POST] Crear una publicación**  
Permite a un usuario autenticado crear una nueva publicación en el foro.  

- **URL**: `/api/posts`  
- **Autorización**: Requiere autenticación JWT.  
- **Request Body (JSON)**:  
  ```json
  {
      "message": "Contenido de la publicación",
      "author": "Nombre del autor",
      "topic": "Nombre del tema"
  }

#### **[PUT, DELETE, GET] post**  
- **URL**: `/api/posts/id`  
- **Autorización**: Requiere autenticación JWT.  
- **Request Body (JSON)**
  
### **1. Clonar el repositorio**  
Clona este repositorio en tu máquina local:  
```bash
git clone https://github.com/tu-usuario/foro-hub-api.git
cd foro-hub-api
```
## Configuracion
### Instalar dependencias
``` bash
mvn clean install
```
### Ejecutar la applicacion
``` bash
mvn spring-boot:run
```
