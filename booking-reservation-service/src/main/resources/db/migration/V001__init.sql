DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'reservation_status') 
        THEN CREATE TYPE reservation_status AS ENUM ('PENDING', 'APPROVED', 'CANCELED', 'REJECTED');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS reservation (
    id UUID PRIMARY KEY,
    accommodation_id UUID NOT NULL,
    user_id UUID NOT NULL,
    beginning DATE NOT NULL,
    ending DATE NOT NULL,
    guests INT NOT NULL,
    status reservation_status NOT NULL
);
