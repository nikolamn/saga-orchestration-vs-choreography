DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'status_enum') 
        THEN CREATE TYPE status_enum AS ENUM ('CREATED', 'DELETED');
    END IF;
END$$;


ALTER TABLE auth_user 
ADD COLUMN IF NOT EXISTS status status_enum NOT NULL DEFAULT 'CREATED';