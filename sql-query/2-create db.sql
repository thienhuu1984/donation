CREATE DATABASE  IF NOT EXISTS `prj_donation` ;

ALTER SCHEMA `prj_donation`  DEFAULT CHARACTER SET utf8 ;

USE `prj_donation`;

CREATE TABLE `donation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) ,
  `name` nvarchar(500) ,
  `organization_name` nvarchar(500) ,
  `description` nvarchar(5000) ,
  `phone_number` varchar(20),
  `money` long,
  `start_date` datetime,
  `end_date` datetime,
  `created` datetime,
  `status` int(11),
  `delete` int(11),
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) ,
  `description` nvarchar(500) ,
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50),
  `password` varchar(50) ,
  `full_name` nvarchar(500) ,
  `phone_number` varchar(20),
  `email` varchar(100),
  `address` nvarchar(1000),
  `role_id` int(11),
  `note` nvarchar(500) ,
  `created` datetime,
  `status` int(11),
  `delete` int(11),
  PRIMARY KEY (`id`)
) ;


CREATE TABLE `user_donation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11),
  `hidden_user` int,
  `donation_id` int(11),
  `created` datetime,
  `money` int,
  `message` nvarchar(500),
  `status` int(11),
  PRIMARY KEY (`id`)
) ;

ALTER TABLE `user`
ADD FOREIGN KEY (`role_id`) REFERENCES `role`(`id`);

ALTER TABLE `user_donation`
ADD FOREIGN KEY (`user_id`) REFERENCES `user`(`id`);


ALTER TABLE `user_donation`
ADD FOREIGN KEY (`donation_id`) REFERENCES `donation`(`id`);


CREATE TABLE `site_info` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `current_page` nvarchar(500),
    `previous_page` nvarchar(500),
    `page_num` int(11),
    `row_per_page` int(11),
  PRIMARY KEY (`id`)
)

