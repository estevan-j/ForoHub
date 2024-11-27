CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,           -- Unique identifier for each user (auto-increment)
    username VARCHAR(50)  NOT NULL UNIQUE, -- Username must be unique and not null
    password VARCHAR(255) NOT NULL         -- Password field to store hashed passwords
);

INSERT INTO users(username, password) VALUES ("adminForo", "27f92a771beb81c342977188dd65477d241c4f31fb3a8f633a6b6a426d6a1e3c");
