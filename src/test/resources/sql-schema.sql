DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `orders_items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `items` (
	`item_id` int(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) DEFAULT NULL,
    `price` DOUBLE DEFAULT NULL,
    PRIMARY KEY (`item_id`) );
    
CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `qty` INT(4) NOT NULL,
    `cost` DOUBLE(6,2) DEFAULT NULL,
    `id` int(11),
    `item_id` int(11),
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`id`) REFERENCES `customers`(`id`),
    FOREIGN KEY (`item_id`) REFERENCES `items`(`item_id`) );
    
CREATE  TABLE IF NOT EXISTS `orders_items` (
	`order_item_id` int(11) NOT NULL AUTO_INCREMENT,
	`item_id` int(11) NOT NULL,
    `order_id` int(11) NOT NULL,
    PRIMARY KEY (`order_item_id`),
    FOREIGN KEY (`item_id`) REFERENCES `items`(`item_id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`));
    
    SELECT o.order_id, c.id, i.item_id
FROM (((orders o JOIN customers c ON o.id = c.id)
JOIN orders_items oi ON o.order_id = oi.order_id)
JOIN items i ON oi.item_id = i.item_id)
GROUP BY o.order_id
ORDER BY o.order_id;