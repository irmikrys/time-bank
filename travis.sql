CREATE DATABASE IF NOT EXISTS timebank;

USE timebank;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
username VARCHAR(30) NOT NULL,
email VARCHAR(50) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
password VARCHAR(255) NOT NULL,
role VARCHAR(30) NOT NULL,
PRIMARY KEY (username)
);

DROP TABLE IF EXISTS advert;
CREATE TABLE advert (
id_advert int(30) NOT NULL AUTO_INCREMENT,
username varchar(30) NOT NULL,
title varchar(50) NOT NULL,
description varchar(280) NOT NULL,
date datetime NOT NULL,
PRIMARY KEY (id_advert)
);
