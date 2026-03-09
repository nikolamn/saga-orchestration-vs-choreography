DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role') 
        THEN CREATE TYPE ROLE AS ENUM ('UNAUTHENTICATED', 'HOST', 'GUEST');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS auth_user (
    id UUID PRIMARY KEY,
    username VARCHAR(20) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ROLE NOT NULL,
    CONSTRAINT username_length_check CHECK (char_length(username) BETWEEN 12 AND 20),
    CONSTRAINT username_unique_check UNIQUE (username)
);

CREATE INDEX idx_auth_user_username ON auth_user(username);