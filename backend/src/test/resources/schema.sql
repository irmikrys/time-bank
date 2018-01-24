DROP SCHEMA IF EXISTS timebankTest;
CREATE SCHEMA timebankTest;
USE timebankTest;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  username      VARCHAR(30)     NOT NULL,
  password      VARCHAR(60)     NOT NULL,
  email         VARCHAR(50)     NOT NULL,
  firstName     VARCHAR(30)     NOT NULL,
  lastName      VARCHAR(30)     NOT NULL,
  role          VARCHAR(30)     NOT NULL,
  photo         MEDIUMBLOB      DEFAULT NULL,
  idLocation    INTEGER(30)     NOT NULL,
  PRIMARY KEY (username)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
  accountNr     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  owner         VARCHAR(30)     NOT NULL,
  balance       INTEGER(11)     NOT NULL,
  PRIMARY KEY (accountNr)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS adverts;
CREATE TABLE adverts (
  idAdvert      INTEGER(30)     NOT NULL AUTO_INCREMENT,
  active        BOOLEAN         NOT NULL,
  type          VARCHAR(5)      NOT NULL,
  owner         VARCHAR(30)     NOT NULL,
  contractor    VARCHAR(30)     DEFAULT NULL,
  title         VARCHAR(80)     NOT NULL,
  description   VARCHAR(270)    NOT NULL,
  idCategory    INTEGER(30)     NOT NULL,
  value         INTEGER(11)     NOT NULL,
  createDate    DATETIME        NOT NULL,
  idLocation    INTEGER(30)     NOT NULL,
  PRIMARY KEY (idAdvert)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS interested;
CREATE TABLE interested (
  idAdvert      INTEGER(30)     NOT NULL,
  interested    VARCHAR(30)     NOT NULL,
  PRIMARY KEY (idAdvert, interested)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  idCategory    INTEGER(30)     NOT NULL AUTO_INCREMENT,
  name          VARCHAR(60)     NOT NULL,
  PRIMARY KEY (idCategory)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS locations;
CREATE TABLE  locations (
  idLocation    INTEGER(30)      NOT NULL AUTO_INCREMENT,
  description   VARCHAR(150)     NOT NULL,
  latitude      NUMERIC(19,16)   NOT NULL,
  longitude     NUMERIC(19,15)   NOT NULL,
  PRIMARY KEY (idLocation)
) ENGINE = innodb DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS archive;
CREATE TABLE archive (
  idAdvert      INTEGER(30)     NOT NULL AUTO_INCREMENT,
  type          VARCHAR(5)      NOT NULL,
  owner         VARCHAR(30)     NOT NULL,
  contractor    VARCHAR(30)     NOT NULL,
  title         VARCHAR(80)     NOT NULL,
  description   VARCHAR(270)    NOT NULL,
  idCategory    INTEGER(30)     NOT NULL,
  value         INTEGER(11)     NOT NULL,
  createDate    DATETIME        NOT NULL,
  closeDate     DATETIME        NOT NULL,
  PRIMARY KEY (idAdvert)
) ENGINE = innodb DEFAULT CHARSET = utf8;
