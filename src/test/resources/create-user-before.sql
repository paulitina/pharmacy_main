DELETE FROM order_products;
DELETE FROM orders;
DELETE FROM users;

ALTER SEQUENCE user_sequence RESTART WITH 2;

INSERT INTO users(user_id, user_name, email, password_hash, password_salt, type) VALUES
(1, 'userName', 'email', 'passwordHash', 'passwordSalt', 'user')
