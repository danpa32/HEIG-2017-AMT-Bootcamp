DROP SCHEMA IF EXISTS db_quotes;
CREATE SCHEMA db_quotes;

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'adminpw';
GRANT ALL PRIVILEGES ON db_quotes.* TO 'admin'@'%';

USE db_quotes;
