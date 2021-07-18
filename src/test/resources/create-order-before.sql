DELETE FROM order_products;
DELETE FROM orders;
DELETE FROM users;
DELETE FROM products;

ALTER SEQUENCE order_sequence RESTART WITH 1;

insert into users(user_id, user_name, email, password_hash, password_salt, type) values
(1, 'userName', 'email', 'passwordHash', 'passwordSalt', 'user');

insert into products(product_id, name, indications, manufacturer_info, side_effects, quantity, price, prescribed, image) values
(1, 'ProductName', 'indications', 'manufacturerInfo', 'sideEffects', 10, 1000, true, null);

insert into products(product_id, name, indications, manufacturer_info, side_effects, quantity, price, prescribed, image) values
(2, 'ProductName2', 'indications2', 'manufacturerInfo2', 'sideEffects2', 20, 2000, false , null);

insert into orders(order_id, user_id, status, address) values
(1, 1, 'cart', 'address');

insert into order_products(order_id, product_id, quantity) values
(1, 2, 2);

insert into order_products(order_id, product_id, quantity) values
(1, 1, 5);

