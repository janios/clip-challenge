DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS payment;

CREATE TABLE `user` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `create_ts` timestamp NULL DEFAULT NULL,
  `last_ts` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `payment` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) NOT NULL,
  'user_id' varchar(200) NOT NULL,
  `create_ts` timestamp NULL DEFAULT NULL,
  `last_ts` timestamp NULL DEFAULT NULL,
  `status` varchar(200) NOT NULL DEFAULT 'NEW',
  PRIMARY KEY (`id`)
);

