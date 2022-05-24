INSERT INTO users (name, surname, email, password, token, created_account, is_admin)
VALUES ('Pero', 'Perić', 'pero.peric@gmail.com', 'sfhhnsn873r87gav832', 'sdnsaouh839r23r9n2', '2016-06-22 19:10:25', 0);
INSERT INTO users (name, surname, email, password, token, created_account, is_admin)
VALUES ('Ivo', 'Ivić', 'ivo.ivic@gmail.com', '63uzegGJFSVU6fvequfz', 'niub9GJVgd7', '2017-04-23 13:15:15', 0);
INSERT INTO users (name, surname, email, password, token, created_account, is_admin)
VALUES ('Admin', 'Adminić', 'admin.adminic@gmail.com', 'hf62387rbdwz83b28', 'dggebZU7HGbh7v', '2015-06-22 09:10:34', 1);

INSERT INTO credit_cards (name, surname, iban, control_number, worth_date)
VALUES ('Marko', 'Markić', '0000 1111 2222 3333', '999', '01/22');
INSERT INTO credit_cards (name, surname, iban, control_number, worth_date)
VALUES ('Ivan', 'Ivanić', '0000 2222 4444 6666', '012', '12/28');
INSERT INTO credit_cards (name, surname, iban, control_number, worth_date)
VALUES ('Ante', 'Antić', '0123 4567 8900 0000', '453', '11/22');
INSERT INTO credit_cards (name, surname, iban, control_number, worth_date)
VALUES ('Dalibor', 'Daliborić', '1111 3333 5555 7777', '666', '04/24');

INSERT INTO regions (name, country) VALUES ('Piedmont', 'Italija');
INSERT INTO regions (name, country) VALUES ('Champagne', 'Francuska');
INSERT INTO regions (name, country) VALUES ('Baranja', 'Hrvatska');
INSERT INTO regions (name, country) VALUES ('Dalmacija', 'Hrvatska');

INSERT INTO categories (name) VALUES ('boja');
INSERT INTO categories (name) VALUES ('sorta');
INSERT INTO categories (name) VALUES ('sladilo');

INSERT INTO wineries (name, founding_year, region_id) VALUES ('Vinček', 1920, 3);
INSERT INTO wineries (name, founding_year, region_id) VALUES ('Dalmoš', 1967, 4);
INSERT INTO wineries (name, founding_year, region_id) VALUES ('Milano', 1927, 1);
INSERT INTO wineries (name, founding_year, region_id) VALUES ('Le Vino', 1888, 2);

INSERT INTO orders (date_and_time, total_cost, address, user_id, credit_card_id)
VALUES ('2016-07-27 19:18:32', 450, 'Trg bana Jelačića 1, 10000 Zagreb', 1, 1);
INSERT INTO orders (date_and_time, total_cost, address, user_id, credit_card_id)
VALUES ('2018-08-08 08:18:08', 1050, 'Trg bana Jelačića 1, 10000 Zagreb', 1, 2);
INSERT INTO orders (date_and_time, total_cost, address, user_id, credit_card_id)
VALUES ('2019-07-14 14:52:45', 100, 'Marjan 1, 21000 Split', 2, 3);
INSERT INTO orders (date_and_time, total_cost, address, user_id, credit_card_id)
VALUES ('2019-02-24 04:22:44', 100, 'Kantrida 10, 51000 Rijeka', 2, 3);

INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Champagne de la Fantastic', 2010, 17.2, 1000, 200,
        'https://cdn.pixabay.com/photo/2016/11/10/19/20/champagne-1814988_960_720.png', 2);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Best champagne ever', 2015, 20.2, 2000, 500,
        'https://cdn.pixabay.com/photo/2012/04/15/21/02/champagne-35313_960_720.png', 2);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Brilliant champagne', 2017, 18, 1500, 400,
        'https://cdn.pixabay.com/photo/2013/07/12/13/21/champagne-146885__340.png', 2);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Jupiter Finest', 2000, 12, 750, 5000,
        'https://cdn.pixabay.com/photo/2013/07/12/13/21/champagne-146885__340.png', 1);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Best Luigi wine', 2015, 13, 750, 100,
        'https://cdn.pixabay.com/photo/2013/07/12/16/28/wine-150955__340.png', 1);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Veronino', 2019, 14.5, 1000, 500,
        'https://images.pexels.com/photos/3490355/pexels-photo-3490355.jpeg?auto=compress&cs=tinysrgb&w=' ||
        '1260&h=750&dpr=1', 1);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Ares godlike wine', 2016, 15, 750, 300,
        'https://images.pexels.com/photos/2912108/pexels-photo-2912108.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260', 1);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Best Luigi wine', 2015, 13, 750, 100,
        'https://cdn.pixabay.com/photo/2013/07/12/16/28/wine-150955__340.png', 1);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Graševina stolna', 2020, 15, 750, 150,
        'https://images.pexels.com/photos/724092/pexels-photo-724092.jpeg?auto=compress&cs' ||
        '=tinysrgb&w=1260&h=750&dpr=1', 3);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Vino za zabave', 2020, 14.4, 1000, 100,
        'https://img.freepik.com/free-psd/wine-bottle-mockup_358694-212.jpg?t=st=1649521094~exp=1' ||
        '649521694~hmac=b53ca291bee678f960ba3b6cda70974099d67b9dd8bd8c41c93120f979f8a7bd&w=1380', 3);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Baranjska ljetna noć', 2019, 13.4, 750, 120,
        'https://img.freepik.com/free-psd/wine-bottle-label-mockup-isolated_72104-3018.jpg?w=1060', 3);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Dalmatinska bura', 2018, 17.5, 1500, 800,
        'https://cdn.pixabay.com/photo/2016/08/06/16/36/wine-1574625_960_720.jpg', 4);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('Životni đir', 2019, 13, 1000, 100,
        'https://images.pexels.com/photos/10963571/pexels-photo-10963571.jpeg?auto=' ||
        'compress&cs=tinysrgb&w=1260&h=750&dpr=1', 4);
INSERT INTO wines (name, production_year, alcohol_percentage, volume, price, picture_url, winery_id)
VALUES ('VAGABUNDO', 2010, 14.4, 1000, 330,
        'https://img.freepik.com/free-psd/wine-bottle-branding-mockup_358694-2576.jpg?w=1380&t=st=1649' ||
        '522441~exp=1649523041~hmac=5046e0dc93caf216d09ae1f810eb0062ab1f5a1d2705ec0cfe5d9f64c8bbb8ee', 4);

INSERT INTO products (wine_id, order_id) VALUES (1, NULL);
INSERT INTO products (wine_id, order_id) VALUES (1, NULL);
INSERT INTO products (wine_id, order_id) VALUES (1, NULL);
INSERT INTO products (wine_id, order_id) VALUES (1, 1);
INSERT INTO products (wine_id, order_id) VALUES (1, 2);
INSERT INTO products (wine_id, order_id) VALUES (2, NULL);
INSERT INTO products (wine_id, order_id) VALUES (2, NULL);
INSERT INTO products (wine_id, order_id) VALUES (2, NULL);
INSERT INTO products (wine_id, order_id) VALUES (2, 3);
INSERT INTO products (wine_id, order_id) VALUES (2, 2);
INSERT INTO products (wine_id, order_id) VALUES (2, 1);
INSERT INTO products (wine_id, order_id) VALUES (3, NULL);
INSERT INTO products (wine_id, order_id) VALUES (3, NULL);
INSERT INTO products (wine_id, order_id) VALUES (3, NULL);
INSERT INTO products (wine_id, order_id) VALUES (3, 2);
INSERT INTO products (wine_id, order_id) VALUES (4, NULL);
INSERT INTO products (wine_id, order_id) VALUES (4, NULL);
INSERT INTO products (wine_id, order_id) VALUES (4, NULL);
INSERT INTO products (wine_id, order_id) VALUES (4, 1);
INSERT INTO products (wine_id, order_id) VALUES (4, 1);
INSERT INTO products (wine_id, order_id) VALUES (5, NULL);
INSERT INTO products (wine_id, order_id) VALUES (5, NULL);
INSERT INTO products (wine_id, order_id) VALUES (5, NULL);
INSERT INTO products (wine_id, order_id) VALUES (5, 2);
INSERT INTO products (wine_id, order_id) VALUES (6, NULL);
INSERT INTO products (wine_id, order_id) VALUES (6, NULL);
INSERT INTO products (wine_id, order_id) VALUES (6, NULL);
INSERT INTO products (wine_id, order_id) VALUES (6, 4);
INSERT INTO products (wine_id, order_id) VALUES (6, 3);
INSERT INTO products (wine_id, order_id) VALUES (7, NULL);
INSERT INTO products (wine_id, order_id) VALUES (7, 1);
INSERT INTO products (wine_id, order_id) VALUES (7, 1);
INSERT INTO products (wine_id, order_id) VALUES (7, 2);
INSERT INTO products (wine_id, order_id) VALUES (8, NULL);
INSERT INTO products (wine_id, order_id) VALUES (8, NULL);
INSERT INTO products (wine_id, order_id) VALUES (8, 3);
INSERT INTO products (wine_id, order_id) VALUES (9, 4);
INSERT INTO products (wine_id, order_id) VALUES (9, 4);
INSERT INTO products (wine_id, order_id) VALUES (9, 3);
INSERT INTO products (wine_id, order_id) VALUES (9, 1);
INSERT INTO products (wine_id, order_id) VALUES (10, NULL);
INSERT INTO products (wine_id, order_id) VALUES (10, NULL);
INSERT INTO products (wine_id, order_id) VALUES (10, NULL);
INSERT INTO products (wine_id, order_id) VALUES (10, NULL);
INSERT INTO products (wine_id, order_id) VALUES (10, 1);
INSERT INTO products (wine_id, order_id) VALUES (11, 1);
INSERT INTO products (wine_id, order_id) VALUES (11, 2);
INSERT INTO products (wine_id, order_id) VALUES (11, 3);
INSERT INTO products (wine_id, order_id) VALUES (11, 4);
INSERT INTO products (wine_id, order_id) VALUES (11, NULL);
INSERT INTO products (wine_id, order_id) VALUES (12, NULL);
INSERT INTO products (wine_id, order_id) VALUES (12, NULL);
INSERT INTO products (wine_id, order_id) VALUES (13, 1);
INSERT INTO products (wine_id, order_id) VALUES (13, 1);
INSERT INTO products (wine_id, order_id) VALUES (13, 1);
INSERT INTO products (wine_id, order_id) VALUES (14, 2);
INSERT INTO products (wine_id, order_id) VALUES (14, NULL);

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











