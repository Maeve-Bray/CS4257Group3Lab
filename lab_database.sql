CREATE DATABASE IF NOT EXISTS lab_db;
USE lab_db;

--  tables with only a primary key on orders (changed in Part 3) 
CREATE TABLE users (
    id BIGINT,
    username VARCHAR(255),
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50)
);

CREATE TABLE products (
    id BIGINT,
    name VARCHAR(500),
    description TEXT,
    price DECIMAL(10,2),
    category VARCHAR(255),
    in_stock BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE TABLE orders (
    id BIGINT PRIMARY KEY,
    user_id BIGINT,
    product_id BIGINT,
    quantity INT,
    total_amount DECIMAL(10,2),
    order_date TIMESTAMP,
    status VARCHAR(50)
);

-- large amounts of data
-- 100,000 users
DELIMITER $$
CREATE PROCEDURE GenerateSampleData()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 100000 DO
        INSERT INTO users (id, username, email, first_name, last_name, status)
        VALUES (i, CONCAT('user', i), CONCAT('user', i, '@example.com'),
                CONCAT('FirstName', i), CONCAT('LastName', i),
                IF(RAND() > 0.5, 'ACTIVE', 'INACTIVE'));
        SET i = i + 1;
    END WHILE;

    -- 50,000 products
    SET i = 1;
    WHILE i <= 50000 DO
        INSERT INTO products (id, name, description, price, category, in_stock)
        VALUES (i, CONCAT('Product ', i),
                CONCAT('Description for product ', i, ' with many details...'),
                ROUND(RAND() * 1000, 2),
                ELT(1 + FLOOR(RAND() * 5), 'Electronics', 'Books', 'Clothing', 'Home', 'Sports'),
                RAND() > 0.1);
        SET i = i + 1;
    END WHILE;

    -- 1,000,000 orders 
    SET i = 1;
    WHILE i <= 1000000 DO
        INSERT INTO orders (id, user_id, product_id, quantity, total_amount, order_date, status)
        VALUES (i,
                1 + FLOOR(RAND() * 100000),  
                1 + FLOOR(RAND() * 50000),   
                1 + FLOOR(RAND() * 10),
                ROUND(RAND() * 1000, 2),
                DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 365) DAY),
                ELT(1 + FLOOR(RAND() * 4), 'PENDING', 'COMPLETED', 'SHIPPED', 'CANCELLED'));
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL GenerateSampleData();
DROP PROCEDURE GenerateSampleData;
