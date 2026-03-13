DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'gender_enum') 
        THEN CREATE TYPE gender_enum AS ENUM ('MALE', 'FEMALE', 'OTHER');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS account (
    id UUID PRIMARY KEY,
    auth_user_id UUID NOT NULL UNIQUE,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    gender gender_enum NOT NULL,
    birthdate DATE NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    
    address_country VARCHAR(50) NOT NULL,
    address_city VARCHAR(50) NOT NULL,
    address_street VARCHAR(50) NOT NULL,
    address_num INTEGER NOT NULL CHECK (address_num > 0),
    CONSTRAINT first_name_length_check CHECK (char_length(first_name) BETWEEN 2 AND 20),
    CONSTRAINT last_name_length_check CHECK (char_length(last_name) BETWEEN 2 AND 20)
);

CREATE INDEX idx_account_email ON account(email);

CREATE INDEX idx_account_auth_user_id ON account(auth_user_id);
