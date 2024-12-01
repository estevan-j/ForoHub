CREATE TABLE posts
(
    id         SERIAL PRIMARY KEY,       -- SERIAL para clave primaria
    message    TEXT         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    author     VARCHAR(255) ,
    topic_id   bigint unsigned NOT NULL, -- Clave foránea que coincide con el tipo SERIAL
    FOREIGN KEY (topic_id) REFERENCES topics (id) ON DELETE CASCADE
);
