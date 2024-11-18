CREATE DATABASE Available_food;
use Available_food;
CREATE TABLE FoodItems (
  order_id INT  PRIMARY KEY,
  food_name VARCHAR(100),
  quantity INT,
  cost DECIMAL(10, 2)
);
drop table FoodItems;
CREATE TABLE FoodItems (
  order_id INT AUTO_INCREMENT PRIMARY KEY,
  food_name VARCHAR(100),
  quantity INT,
  cost DECIMAL(10, 2)
);
SHOW TABLES;
INSERT INTO FoodItems (food_name, quantity, cost) 
VALUES ('Dosa', 4, 40);
SELECT * FROM FoodItems;
TRUNCATE TABLE FoodItems;
SELECT * FROM FoodItems;
SHOW TABLES;
SELECT * FROM FoodItems;
use Available_food;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
SELECT * FROM FoodItems;
use Available_food;












