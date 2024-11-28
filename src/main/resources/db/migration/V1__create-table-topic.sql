CREATE TABLE topics
(
    id         SERIAL PRIMARY KEY,                  -- Identificador único (autoincremental)
    title      VARCHAR(255) NOT NULL,               -- Título del tópico
    message    TEXT         NOT NULL,               -- Mensaje del tópico
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de creación con valor predeterminado
    author     VARCHAR(100) NOT NULL,               -- Autor del tópico
    course     VARCHAR(100) NOT NULL                -- Curso asociado
);
