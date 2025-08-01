-- Habilitar el uso de UUIDs
-- En MySQL 8 puedes usar UUID() directamente

-- Tabla de usuarios
CREATE TABLE users (
                       id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de cursos
CREATE TABLE courses (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL UNIQUE,
                         description TEXT
);

-- Tabla de categorías
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            description TEXT,
                            course_id INT NOT NULL,
                            FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
                            UNIQUE (name, course_id)
);

-- Tabla de posts
CREATE TABLE posts (
                       id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
                       title VARCHAR(200) NOT NULL,
                       content TEXT NOT NULL,
                       status BOOLEAN  NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       user_id CHAR(36) NOT NULL,
                       course_id INT,
                       FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                       FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE SET NULL
);

-- Tabla de comentarios
CREATE TABLE comments (
                          id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
                          content TEXT NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          user_id CHAR(36) NOT NULL,
                          post_id CHAR(36) NOT NULL,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                          FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);

-- Tabla de reacciones
CREATE TABLE reactions (
                           id CHAR(36) PRIMARY KEY DEFAULT (UUID()),
                           user_id CHAR(36) NOT NULL,
                           post_id CHAR(36),
                           comment_id CHAR(36),
                           type VARCHAR(20) NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                           FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,
                           FOREIGN KEY (comment_id) REFERENCES comments(id) ON DELETE CASCADE,
                           CHECK (
                               (post_id IS NOT NULL AND comment_id IS NULL)
                                   OR
                               (post_id IS NULL AND comment_id IS NOT NULL)
                               )
);

-- Usuarios para pruebas
INSERT INTO users (username, email, password)
VALUES
    (
        'claudia',
        'claudia@example.com',
        '$2a$12$RcI7SVhkumZup88wVMLFsOOR9fjMnNCFNsaN/JATSYK60s1XSHXkG' -- claudia123
    ),
    (
        'martin',
        'martin@example.com',
        '$2a$12$8WcaX3HAv6EVI6ikfP8dD.VMHGOcN88.E2BxlAWH1gt2Xw5M3CJmi' -- martin123
    );

-- Un curso para iniciar
INSERT INTO courses (name, description)
VALUES ('Introducción a la Programación', 'Curso básico para aprender lógica y fundamentos de programación.');

-- Categorias para iniciar
INSERT INTO categories (name, description, course_id)
VALUES
    ('Discusión General', 'Temas abiertos relacionados con el curso.', 1),
    ('Dudas de Clase', 'Consulta sobre contenidos específicos.', 1),
    ('Recursos', 'Comparte enlaces, videos o apuntes.', 1),
    ('Sugerencias', 'Sugerencias para mejorar el curso o las clases.', 1);