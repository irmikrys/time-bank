CREATE DATABASE IF NOT EXISTS timebank;

USE timebank;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username      VARCHAR(30)   NOT NULL,
  first_name    VARCHAR(30)   NOT NULL,
  last_name     VARCHAR(30)   NOT NULL,
  role          VARCHAR(30)   NOT NULL,
  password      VARCHAR(60)   NOT NULL,
  id_location   INT(30)       NOT NULL,
  email         VARCHAR(50)   NOT NULL,
  photo         GEOMETRY      DEFAULT NULL,
  PRIMARY KEY (username)
);

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  account_nr    INT(30)       NOT NULL AUTO_INCREMENT,
  username      VARCHAR(30)   NOT NULL,
  amount        INT(30)       NOT NULL,
  PRIMARY KEY (account_nr)
);

DROP TABLE IF EXISTS adverts;
CREATE TABLE adverts (
  id_advert     INT(30)       NOT NULL AUTO_INCREMENT,
  username      VARCHAR(30)   NOT NULL,
  type          VARCHAR(10)   NOT NULL,
  id_category   INT(30)       NOT NULL,
  title         VARCHAR(80)   NOT NULL,
  description   VARCHAR(270)  NOT NULL,
  value         INT(30)       NOT NULL,
  id_location   INT(30)       NOT NULL,
  create_date   DATETIME      NOT NULL,
  performer     VARCHAR(30)   DEFAULT NULL,
  active        BOOLEAN       NOT NULL,
  PRIMARY KEY (id_advert)
);

DROP TABLE IF EXISTS interested;
CREATE TABLE interested (
  id_advert     INT(30)       NOT NULL,
  interested    VARCHAR(30)   NOT NULL,
  PRIMARY KEY (id_advert, interested)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  id_category   INT(30)       NOT NULL AUTO_INCREMENT,
  name          VARCHAR(60)   NOT NULL,
  PRIMARY KEY (id_category)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE  locations (
  id_location   INT(30)         NOT NULL AUTO_INCREMENT,
  description   VARCHAR(60)     NOT NULL,
  latitude      NUMERIC(18,14)  NOT NULL,
  longitude     NUMERIC(18,14)  NOT NULL,
  PRIMARY KEY (id_location)
);

DROP TABLE IF EXISTS archive;
CREATE TABLE archive (
  id_advert     INT(30)       NOT NULL AUTO_INCREMENT,
  employer      VARCHAR(30)   NOT NULL,
  performer     VARCHAR(30)   NOT NULL,
  id_category   INT(30)       NOT NULL,
  title         VARCHAR(80)   NOT NULL,
  description   VARCHAR(270)  NOT NULL,
  create_date   DATETIME      NOT NULL,
  close_datte   DATETIME      NOT NULL,
  value         INT(30)       NOT NULL,
  PRIMARY KEY (id_advert)
);

INSERT INTO categories (id_category, name) VALUES
  (1, "zdrowie"),
  (2, "spoleczne"),
  (3, "nauka");
