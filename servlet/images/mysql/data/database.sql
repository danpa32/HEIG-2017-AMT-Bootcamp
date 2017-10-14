DROP SCHEMA IF EXISTS db_quotes;
CREATE SCHEMA db_quotes;

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'adminpw';
GRANT ALL PRIVILEGES ON db_quotes.* TO 'admin'@'%';

USE db_quotes;

CREATE TABLE IF NOT EXISTS quote (
	id INT(4) NOT NULL AUTO_INCREMENT,
	quote TEXT NOT NULL,
	author VARCHAR(50),
	date INT,
	source VARCHAR(40),
	category VARCHAR(30),
	PRIMARY KEY (id)
);
