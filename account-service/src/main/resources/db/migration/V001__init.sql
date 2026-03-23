DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'status_enum') 
        THEN CREATE TYPE status_enum AS ENUM ('CREATED', 'PENDING_DELETION', 'DELETED');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS account (
    id UUID PRIMARY KEY,
    auth_user_id UUID NOT NULL UNIQUE,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    birthdate DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    status status_enum NOT NULL DEFAULT 'CREATED',

    CONSTRAINT first_name_length_check CHECK (char_length(first_name) BETWEEN 2 AND 20),
    CONSTRAINT last_name_length_check CHECK (char_length(last_name) BETWEEN 2 AND 20)
);

CREATE INDEX idx_account_email ON account(email);

CREATE INDEX idx_account_auth_user_id ON account(auth_user_id);
