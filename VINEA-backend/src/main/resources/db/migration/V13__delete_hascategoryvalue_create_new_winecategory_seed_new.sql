DROP TABLE has_category_value;

CREATE TABLE wine_category
(
    id BIGSERIAL NOT NULL,
    category_id BIGINT NOT NULL,
    wine_id BIGINT NOT NULL,
    value TEXT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (category_id, wine_id),
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (wine_id) REFERENCES wines(id)
);

INSERT INTO wine_category (value, category_id, wine_id) VALUES ('crveno', 1, 4);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('bijelo', 1, 5);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('ružičasto', 1, 7);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('crveno', 1, 8);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('bijelo', 1, 9);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('bijelo', 1, 11);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Šampanjac', 2, 1);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Merlot', 2, 4);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Crni pinot', 2, 5);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Malvazija', 2, 7);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Malvazija', 2, 8);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Stolno', 2, 9);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Rizling', 2, 12);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Stolno', 2, 13);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Poluslatko', 3, 1);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Slatko', 3, 2);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Suho', 3, 3);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Polusuho', 3, 8);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Suho', 3, 9);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Slatko', 3, 11);
INSERT INTO wine_category (value, category_id, wine_id) VALUES ('Poluslatko', 3, 12);

ALTER TABLE IF EXISTS users RENAME TO "user";
ALTER TABLE IF EXISTS credit_cards RENAME TO credit_card;
ALTER TABLE IF EXISTS regions RENAME TO region;
ALTER TABLE IF EXISTS categories RENAME TO category;
ALTER TABLE IF EXISTS wineries RENAME TO winery;
ALTER TABLE IF EXISTS orders RENAME TO "order";
ALTER TABLE IF EXISTS wines RENAME TO wine;
ALTER TABLE IF EXISTS products RENAME TO product;
