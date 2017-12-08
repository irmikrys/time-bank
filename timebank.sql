CREATE DATABASE IF NOT EXISTS timebank;

USE timebank;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username      VARCHAR(30)     NOT NULL,
  password      VARCHAR(60)     NOT NULL,
  email         VARCHAR(50)     NOT NULL,
  first_name    VARCHAR(30)     NOT NULL,
  last_name     VARCHAR(30)     NOT NULL,
  role          VARCHAR(30)     NOT NULL,
  photo         MEDIUMBLOB      DEFAULT NULL,
  id_location   INTEGER(30)     NOT NULL,
  PRIMARY KEY (username)
);

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  account_nr    INTEGER(30)     NOT NULL AUTO_INCREMENT,
  owner         VARCHAR(30)     NOT NULL,
  balance       INTEGER(11)     NOT NULL,
  PRIMARY KEY (account_nr)
);

DROP TABLE IF EXISTS adverts;
CREATE TABLE adverts (
  id_advert     INTEGER(30)     NOT NULL AUTO_INCREMENT,
  active        BOOLEAN         NOT NULL,
# type : {"SEEK", "OFFER"} <- dostepne opcje
  type          VARCHAR(5)      NOT NULL,
  employer      VARCHAR(30)     NOT NULL,
  performer     VARCHAR(30)     DEFAULT NULL,
  title         VARCHAR(80)     NOT NULL,
  description   VARCHAR(270)    NOT NULL,
  id_category   INTEGER(30)     NOT NULL,
  value         INTEGER(11)     NOT NULL,
  create_date   DATETIME        NOT NULL,
  id_location   INTEGER(30)     NOT NULL,
  PRIMARY KEY (id_advert)
);

DROP TABLE IF EXISTS interested;
CREATE TABLE interested (
  id_advert     INTEGER(30)     NOT NULL,
  interested    VARCHAR(30)     NOT NULL,
  PRIMARY KEY (id_advert, interested)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  id_category   INTEGER(30)     NOT NULL AUTO_INCREMENT,
  name          VARCHAR(60)     NOT NULL,
  PRIMARY KEY (id_category)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE  locations (
  id_location   INTEGER(30)     NOT NULL AUTO_INCREMENT,
  description   VARCHAR(60)     NOT NULL,
  latitude      NUMERIC(10,6)   NOT NULL,
  longitude     NUMERIC(10,6)   NOT NULL,
  PRIMARY KEY (id_location)
);

DROP TABLE IF EXISTS archive;
CREATE TABLE archive (
  id_advert     INTEGER(30)     NOT NULL AUTO_INCREMENT,
  type          VARCHAR(5)      NOT NULL,
  employer      VARCHAR(30)     NOT NULL,
  performer     VARCHAR(30)     NOT NULL,
  title         VARCHAR(80)     NOT NULL,
  description   VARCHAR(270)    NOT NULL,
  id_category   INTEGER(30)     NOT NULL,
  value         INTEGER(11)     NOT NULL,
  create_date   DATETIME        NOT NULL,
  close_date    DATETIME        NOT NULL,
  PRIMARY KEY (id_advert)
);

INSERT INTO categories (id_category, name) VALUES
  (1, 'Pet Care'),
  (2, 'Cooking'),
  (3, 'Housekeeping'),
  (4, 'Tutoring');
