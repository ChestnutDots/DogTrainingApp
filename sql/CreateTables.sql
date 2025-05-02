DROP SCHEMA IF EXISTS `dog_training`;

CREATE SCHEMA `dog_training`;

use `dog_training`;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) UNIQUE NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `enabled` BOOLEAN DEFAULT TRUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `users`
VALUES
(1,'admin', '$2a$10$VyBWrOF4RWjEWXmPsOBEv.mNEnr7vmuAZ6gJe1bIduBY7IYwxdpga', true),
(2,'linda', '$2a$10$x/u379WOmm7GUU3KMNI2Su1y3pPokhIADzd.s21QdE5LkPayotDj.', true);

DROP TABLE IF EXISTS `dogs`;

CREATE TABLE `dogs`(
 `id` int NOT NULL AUTO_INCREMENT,
 `name`varchar(100) DEFAULT NULL,
 `user_id` int DEFAULT NULL,
 
 PRIMARY KEY(`id`),
 KEY `FK_USER_idx` (`user_id`),
 
 CONSTRAINT `FK_USER`
 FOREIGN KEY (`user_id`)
 REFERENCES `users`(`id`)
 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `training_sessions`;

CREATE TABLE `training_sessions`(
 `id` int NOT NULL AUTO_INCREMENT,
 `date`varchar(100) DEFAULT NULL,
 `dog_id` int DEFAULT NULL,
 `command`varchar(100) DEFAULT NULL,
 `progress`varchar(100) DEFAULT NULL,
 
 
 PRIMARY KEY(`id`),
 KEY `FK_DOG_idx` (`dog_id`),
 
 CONSTRAINT `FK_DOG`
 FOREIGN KEY (`dog_id`)
 REFERENCES `dogs`(`id`)
 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles`(
`id` int NOT NULL AUTO_INCREMENT,
`user_id` int NOT NULL,
`role` varchar(50) NOT NULL,
PRIMARY KEY(`id`),
FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
);

INSERT INTO `roles` (user_id, role)
VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_USER'),
(1, 'ROLE_ADMIN'); 

SET FOREIGN_KEY_CHECKS=1;
