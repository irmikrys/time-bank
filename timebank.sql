CREATE DATABASE IF NOT EXISTS timebank;

USE timebank;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username      VARCHAR(30)     NOT NULL,
  password      VARCHAR(60)     NOT NULL,
  email         VARCHAR(50)     NOT NULL,
  firstName    VARCHAR(30)     NOT NULL,
  lastName     VARCHAR(30)     NOT NULL,
  role          VARCHAR(30)     NOT NULL,
  photo         MEDIUMBLOB      DEFAULT NULL,
  idLocation   INTEGER(30)     NOT NULL,
  PRIMARY KEY (username)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  accountNr    INTEGER(30)     NOT NULL AUTO_INCREMENT,
  owner         VARCHAR(30)     NOT NULL,
  balance       INTEGER(11)     NOT NULL,
  PRIMARY KEY (accountNr)
);

DROP TABLE IF EXISTS adverts;
CREATE TABLE adverts (
  idAdvert     INTEGER(30)     NOT NULL AUTO_INCREMENT,
  active        BOOLEAN         NOT NULL,
# type : {"SEEK", "OFFER"} <- dostepne opcje
  type          VARCHAR(5)      NOT NULL,
  employer      VARCHAR(30)     NOT NULL,
  performer     VARCHAR(30)     DEFAULT NULL,
  title         VARCHAR(80)     NOT NULL,
  description   VARCHAR(270)    NOT NULL,
  idCategory   INTEGER(30)     NOT NULL,
  value         INTEGER(11)     NOT NULL,
  createDate   DATETIME        NOT NULL,
  idLocation   INTEGER(30)     NOT NULL,
  PRIMARY KEY (idAdvert)
);

DROP TABLE IF EXISTS interested;
CREATE TABLE interested (
  idAdvert     INTEGER(30)     NOT NULL,
  interested    VARCHAR(30)     NOT NULL,
  PRIMARY KEY (idAdvert, interested)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  idCategory   INTEGER(30)     NOT NULL AUTO_INCREMENT,
  name          VARCHAR(60)     NOT NULL,
  PRIMARY KEY (idCategory)
);

DROP TABLE IF EXISTS locations;
CREATE TABLE  locations (
  idLocation   INTEGER(30)     NOT NULL AUTO_INCREMENT,
  description   VARCHAR(60)     NOT NULL,
  latitude      NUMERIC(10,6)   NOT NULL,
  longitude     NUMERIC(10,6)   NOT NULL,
  PRIMARY KEY (idLocation)
);

DROP TABLE IF EXISTS archive;
CREATE TABLE archive (
  idAdvert     INTEGER(30)     NOT NULL AUTO_INCREMENT,
  type          VARCHAR(5)      NOT NULL,
  employer      VARCHAR(30)     NOT NULL,
  performer     VARCHAR(30)     NOT NULL,
  title         VARCHAR(80)     NOT NULL,
  description   VARCHAR(270)    NOT NULL,
  idCategory   INTEGER(30)     NOT NULL,
  value         INTEGER(11)     NOT NULL,
  createDate   DATETIME        NOT NULL,
  closeDate    DATETIME        NOT NULL,
  PRIMARY KEY (idAdvert)
);

INSERT INTO categories (idCategory, name) VALUES
  (1, 'Pet Care'),
  (2, 'Cooking'),
  (3, 'Housekeeping'),
  (4, 'Tutoring'),
  (5, 'Makeup'),
  (6, 'Free Time'),
  (7, 'People\' Care'),
  (8, 'Amusement')
;
