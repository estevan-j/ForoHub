CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,           -- Unique identifier for each user (auto-increment)
    username VARCHAR(50)  NOT NULL UNIQUE, -- Username must be unique and not null
    password VARCHAR(255) NOT NULL         -- Password field to store hashed passwords
);

INSERT INTO users(username, password) VALUES ("adminForo", "0F2C082AEFC8251D1D782A63CD8FBA8205C4299E8469FF4EAA2B7B7E95929834");
