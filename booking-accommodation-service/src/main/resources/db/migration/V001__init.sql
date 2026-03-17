DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'amenity_type') 
        THEN CREATE TYPE amenity_type AS ENUM ('WIFI', 'AIR_CONDITIONING', 'FREE_PARKING', 'KITCHEN', 'WASHING_MACHINE', 'BALCONY', 'BATH_TUB');
    END IF;
END$$;

CREATE TABLE IF NOT EXISTS accommodation (
    id UUID PRIMARY KEY, 
    name VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    owner_id UUID NOT NULL,
    min_guests INTEGER NOT NULL CHECK (min_guests > 0),
    max_guests INTEGER NOT NULL CHECK (max_guests > 0),

    address_country VARCHAR(50) NOT NULL,
    address_city VARCHAR(50) NOT NULL,
    address_street VARCHAR(50) NOT NULL,
    address_num INTEGER NOT NULL CHECK (address_num > 0),

    CONSTRAINT name_length_check CHECK (char_length(name) BETWEEN 5 AND 30),
    CONSTRAINT description_length_check CHECK (char_length(description) BETWEEN 5 AND 100)
);

CREATE TABLE IF NOT EXISTS accommodation_amenity (
    accommodation_id UUID NOT NULL,
    type amenity_type NOT NULL,
    CONSTRAINT fk_accommodation FOREIGN KEY (accommodation_id) REFERENCES accommodation(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS accommodation_availability (
    id UUID PRIMARY KEY,
    accommodation_id UUID NOT NULL,
    beginning DATE NOT NULL,
    ending DATE NOT NULL,
    price BIGINT NOT NULL CHECK (price > 0),
    is_price_per_guest BOOLEAN NOT NULL,
    CHECK (ending >= beginning),
    CONSTRAINT fk_accommodation FOREIGN KEY (accommodation_id) REFERENCES accommodation(id) ON DELETE CASCADE
);