CREATE TABLE users
(
    id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    token TEXT NOT NULL,
    created_account TIMESTAMP NOT NULL,
    is_admin INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id BIGSERIAL NOT NULL,
    date_and_time TIMESTAMP NOT NULL,
    total_cost NUMERIC(8, 2) NOT NULL,
    address TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE credit_cards
(
    id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    surname TEXT NOT NULL,
    iban VARCHAR(20) NOT NULL,
    control_number VARCHAR(3) NOT NULL,
    worth_date VARCHAR(5) NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE regions
(
    id BIGSERIAL NOT NULL,
    country TEXT NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE wineries
(
    id BIGSERIAL NOT NULL,
    founding_year INT NOT NULL,
    name TEXT NOT NULL,
    region_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (region_id) REFERENCES regions(id)
);

CREATE TABLE wines
(
    id BIGSERIAL NOT NULL,
    name TEXT NOT NULL,
    production_year INT NOT NULL,
    alcohol_percentage NUMERIC(4, 2) NOT NULL,
    volume INT NOT NULL,
    price NUMERIC(7, 2) NOT NULL,
    picture_url TEXT NOT NULL,
    winery_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (winery_id) REFERENCES wineries(id)
);

CREATE TABLE products
(
    id BIGSERIAL NOT NULL,
    wine_id BIGINT NOT NULL,
    order_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (wine_id) REFERENCES wines(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE TABLE has_category_value
(
    value TEXT NOT NULL,
    category_id BIGINT NOT NULL,
    wine_id BIGINT NOT NULL,
    PRIMARY KEY (category_id, wine_id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (wine_id) REFERENCES wines(id)
);
