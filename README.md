# ForoHub API REST  

Este proyecto es una API REST desarrollada con **Spring Boot**, diseñada para gestionar un foro en línea llamado **ForoHub**. La API incluye funcionalidades de creación, consulta de publicaciones y temas, además de autenticación y autorización mediante **JWT (JSON Web Tokens)** para garantizar la seguridad de las operaciones.

---

## 📑 Características principales  

- **Gestión de publicaciones**: Crear y consultar publicaciones del foro.
- **Gestión de temas**: Obtener la lista de temas disponibles.
- **Autenticación y autorización**: Acceso protegido con autenticación JWT.
- **Cumplimiento de seguridad**: Roles y permisos para proteger los endpoints.

---

## 🚀 Endpoints de la API  

### **1. `/api/posts`**  

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
