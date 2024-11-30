CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,           -- Unique identifier for each user (auto-increment)
    username VARCHAR(50)  NOT NULL UNIQUE, -- Username must be unique and not null
    password VARCHAR(255) NOT NULL         -- Password field to store hashed passwords
);

INSERT INTO users(username, password) VALUES ("adminForo", "$2a$10$1T0nt6Y0epskf3OwMhrZb.wWXoNHMI4gZ1FBV3S8Hq6jGrw8hiz7.");
