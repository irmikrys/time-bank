CREATE DATABASE IF NOT EXISTS timebank;

USE timebank;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  role VARCHAR(30) NOT NULL,
  password VARCHAR(60) NOT NULL,
  id_location INT(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  photo GEOMETRY,
  PRIMARY KEY (username)
);

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  account_nr INT(30),
  username VARCHAR(30),
  amount INT(30),
  PRIMARY KEY (account_nr)
);

DROP TABLE IF EXISTS adverts;
CREATE TABLE adverts (
  id_advert INT(30) NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  type VARCHAR(10) NOT NULL,
  id_cathegory INT(30) NOT NULL,
  title VARCHAR(80) NOT NULL,
  description VARCHAR(270) NOT NULL,
  value INT(30),
  id_location INT(30) NOT NULL,
  create_date DATETIME NOT NULL,
  performer VARCHAR(30),
  active BOOLEAN,
  PRIMARY KEY (id_advert)
);

DROP TABLE IF EXISTS interested;
CREATE TABLE interested (
  id_advert INT(30),
  interested VARCHAR(30),
  PRIMARY KEY (id_advert, interested)
);

DROP TABLE IF EXISTS cathegories;
CREATE TABLE cathegories (
  id_cathegory INT(30),
  name VARCHAR(60),
  PRIMARY KEY (id_cathegory)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE  locations (
  id_location INT(30),
  description VARCHAR(60),
  latitude NUMERIC(18,14),
  longitude NUMERIC(18,14),
  PRIMARY KEY (id_location)
);
