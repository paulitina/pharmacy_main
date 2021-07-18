DELETE FROM order_products;
DELETE FROM products;

ALTER SEQUENCE product_sequence RESTART WITH 3;

INSERT INTO products(product_id, name, indications, manufacturer_info, side_effects, quantity, price, prescribed, image) VALUES
(1, 'ProductName', 'indications', 'manufacturerInfo', 'sideEffects', 10, 1000, true, null);

INSERT INTO products(product_id, name, indications, manufacturer_info, side_effects, quantity, price, prescribed, image) VALUES
(2, 'ProductName', 'indications', 'manufacturerInfo', 'sideEffects', 10, 1000, true, null);