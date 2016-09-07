CREATE DATABASE IF NOT EXISTS `Summary_Task4_db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `Summary_Task4_db`;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles`(
`id` int (2) unsigned NOT NULL AUTO_INCREMENT,
`name` varchar (15) NOT NULL,
PRIMARY KEY(`id`)
);

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account`(
`id` int (10) unsigned NOT NULL AUTO_INCREMENT,
`balance` decimal (20),
`status` enum ('unblocked', 'blocked') NOT NULL,
PRIMARY KEY(`id`)
);


DROP TABLE IF EXISTS `credit_card`;

CREATE TABLE `credit_card`(
`id` int (10) unsigned NOT NULL AUTO_INCREMENT,
`card_number` int (10) NOT NULL,
`account_id` int(10) unsigned NOT NULL,
PRIMARY KEY(`id`),
FOREIGN KEY (`account_id`) REFERENCES `account`(`id`)
);

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`(
`id` int (10) unsigned NOT NULL AUTO_INCREMENT,
`login` varchar (16) NOT NULL,
`password` varchar (25) NOT NULL,
`first_name` varchar (15) NOT NULL,
`last_name` varchar (20) NOT NULL,
`role_id` int (2) unsigned NOT NULL,
`card_id` int (10) unsigned,
`status` enum ('unblocked', 'blocked') NOT NULL,
PRIMARY KEY(`id`),
FOREIGN KEY (`role_id`) REFERENCES `roles`(`id`), 
FOREIGN KEY(`card_id`) REFERENCES `credit_card`(`id`)
);

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment`(
`id` int (10) unsigned NOT NULL AUTO_INCREMENT,
`amount` decimal (20) unsigned,
`user_id` int (10) unsigned NOT NULL,
`credit_card_id` int (10) unsigned NOT NULL,
`date` DATETIME DEFAULT CURRENT_TIMESTAMP,
`status` enum ('prepared', 'sent'),
PRIMARY KEY(`id`),
FOREIGN KEY (`user_id`) REFERENCES `users`(`id`), 
FOREIGN KEY(`credit_card_id`) REFERENCES `credit_card`(`id`)
);

INSERT INTO roles (name) VALUES ('admin'), ('client');
SELECT * FROM practice10_db.roles;
INSERT INTO users(`login`, `password`, `first_name`, `last_name`, `role_id`) VALUES ('admin', 'admin', 'a', 'a', '1' ), ('client', 'client', 's', 's', '2');
SELECT * FROM practice10_db.users;

 


