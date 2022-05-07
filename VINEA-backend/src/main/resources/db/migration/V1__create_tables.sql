CREATE TABLE IF NOT EXISTS "user"
(
    id BIGSERIAL NOT NULL,
    name VARCHAR(20) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    token TEXT,
    created_account TIMESTAMP NOT NULL,
    is_admin INT NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS credit_card
(
    id BIGSERIAL NOT NULL,
    name VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL,
    iban VARCHAR(20) NOT NULL,
    control_number VARCHAR(3) NOT NULL,
    worth_date VARCHAR(5) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS region
(
    id BIGSERIAL NOT NULL,
    country VARCHAR(50) NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS category
(
    id BIGSERIAL NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS winery
(
    id BIGSERIAL NOT NULL,
    founding_year INT NOT NULL,
    name VARCHAR(40) NOT NULL,
    region_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (region_id) REFERENCES Region(id)
    );

CREATE TABLE IF NOT EXISTS "order"
(
    id BIGSERIAL NOT NULL,
    date_and_time  TIMESTAMP NOT NULL,
    total_cost NUMERIC(8, 2) NOT NULL,
    address VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    credit_card_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (credit_card_id) REFERENCES credit_card(id)
    );

CREATE TABLE IF NOT EXISTS wine
(
    id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    production_year INT NOT NULL,
    alcohol_percentage NUMERIC(4, 2) NOT NULL,
    volume INT NOT NULL,
    price NUMERIC(7, 2) NOT NULL,
    picture_url VARCHAR(300) NOT NULL,
    winery_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (winery_id) REFERENCES winery(id)
    );

CREATE TABLE IF NOT EXISTS product
(
    id BIGSERIAL NOT NULL,
    wine_id BIGINT NOT NULL,
    order_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (wine_id) REFERENCES wine(id),
    FOREIGN KEY (order_id) REFERENCES "order"(id)
    );

CREATE TABLE IF NOT EXISTS wine_category
(
    value VARCHAR(30) NOT NULL,
    category_id BIGINT NOT NULL,
    wine_id BIGINT NOT NULL,
    PRIMARY KEY (category_id, wine_id),
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (wine_id) REFERENCES wine(id)
    );
