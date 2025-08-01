# ForoHub 🧠💬

**ForoHub** es una API REST desarrollada con **Java + Spring Boot**, diseñada para gestionar publicaciones, usuarios y categorías en un foro de discusión. Este proyecto puede ser la base para una plataforma de comunidad o blog técnico.

---

## 🚀 Tecnologías usadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok

---

## ⚙️ Requisitos

- JDK 17+
- Maven 3.8+
- MySQL
- IDE como IntelliJ IDEA o Eclipse

---

## ▶️ Cómo correr el proyecto

1. **Clona el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/forohub.git
   cd forohub
   ```

2. **Configura la base de datos**  
   Edita el archivo `src/main/resources/application.properties` o `application.yml`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/forohub_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Compila y ejecuta la aplicación**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Prueba la API con Postman o curl**  
   La app por defecto se ejecuta en:
   ```
   http://localhost:8080
   ```

---

## 📦 Endpoints básicos

| Método | Ruta            | Descripción                 |
|--------|-----------------|-----------------------------|
| POST   | `/topicos`      | Crear una nueva publicación |
| GET    | `/topicos`      | Listar publicaciones        |
| GET    | `/topicos/{id}` | Obtener publicación por ID  |
| PUT    | `/topicos/{id}` | Editar publicación por ID   |
| DELETE | `/topicos/{id}` | Eliminar publicación por ID |


---

## Swagger

Revisa los endpoints en la documentación oficial. 

```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## 📌 Notas adicionales

- El proyecto usa manejo global de errores con `@RestControllerAdvice`.
- Las validaciones se realizan usando `@Valid` y excepciones personalizadas (`ValidationException`).
- Contiene JWT para que los usuarios se logueen.

---

