DELETE FROM order_products;
DELETE FROM orders;
DELETE FROM users;

ALTER SEQUENCE user_sequence RESTART WITH 2;
