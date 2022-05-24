SELECT setval('categories_id_seq', COALESCE((SELECT MAX(id)+1 FROM categories), 1), false);
SELECT setval('credit_cards_id_seq', COALESCE((SELECT MAX(id)+1 FROM credit_cards), 1), false);
SELECT setval('orders_id_seq', COALESCE((SELECT MAX(id)+1 FROM orders), 1), false);
SELECT setval('products_id_seq', COALESCE((SELECT MAX(id)+1 FROM products), 1), false);
SELECT setval('regions_id_seq', COALESCE((SELECT MAX(id)+1 FROM regions), 1), false);
SELECT setval('users_id_seq', COALESCE((SELECT MAX(id)+1 FROM users), 1), false);
SELECT setval('wineries_id_seq', COALESCE((SELECT MAX(id)+1 FROM wineries), 1), false);
SELECT setval('wines_id_seq', COALESCE((SELECT MAX(id)+1 FROM wines), 1), false);
