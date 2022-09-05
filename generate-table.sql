-- Creates a new database if one doesn't already exist
CREATE DATABASE IF NOT EXISTS poisepms;

-- Select the database
USE poisepms;

-- Create tables
CREATE TABLE project(
PROJECT_NUM int(3) NOT NULL,
PROJECT_NAME varchar(50),
DEADLINE date,
COMPLETE_DATE date,
TOTAL_COST float,
AMOUNT_PAID float,
FINALISED boolean,
ERF_NUMBER varchar(10),
ARCHITECT_ID varchar(5),
CUSTOMER_ID varchar(5),
CONTRACTOR_ID varchar(5),
PRIMARY KEY(PROJECT_NUM));

CREATE TABLE building(
ERF_NUMBER varchar(10) NOT NULL,
BUILDING_ADDRESS varchar(50),
BUILDING_TYPE varchar(50),
PRIMARY KEY(ERF_NUMBER));

CREATE TABLE architect(
ARCHITECT_ID varchar(5) NOT NULL,
ARCHITECT_FNAME varchar(50),
ARCHITECT_LNAME varchar(50),
ARCHITECT_PHONE varchar(50),
ARCHITECT_EMAIL varchar(50),
ARCHITECT_ADDRESS varchar(50),
PRIMARY KEY(ARCHITECT_ID)
);

CREATE TABLE CUSTOMER(
CUSTOMER_ID varchar(5) NOT NULL,
CUSTOMER_FNAME varchar(50),
CUSTOMER_LNAME varchar(50),
CUSTOMER_PHONE varchar(50),
CUSTOMER_EMAIL varchar(50),
CUSTOMER_ADDRESS varchar(50),
PRIMARY KEY(CUSTOMER_ID)
);

CREATE TABLE CONTRACTOR(
CONTRACTOR_ID varchar(5) NOT NULL,
CONTRACTOR_FNAME varchar(50),
CONTRACTOR_LNAME varchar(50),
CONTRACTOR_PHONE varchar(50),
CONTRACTOR_EMAIL varchar(50),
CONTRACTOR_ADDRESS varchar(50),
PRIMARY KEY(CONTRACTOR_ID)
);

-- Populate tables with data
INSERT INTO project VALUES
(1, "Wayne Manor", "2023-04-05", NULL, 67000.73, 3000, FALSE, "1234567890", "12345", "12345", "12345"),
(2, "Bel Air Mansion", "2022-04-05", "2022-04-09", 64340.21, 2000, TRUE, "0987654321", "54321", "54321", "54321")
;

INSERT INTO building VALUES
("1234567890", "12 Gotham Road", "Manor"),
("0987654321", "251N Bristol Avenue", "Mansion")
;

INSERT INTO architect VALUES
("12345", "Ted", "Moseby", "0784521221", "tedmoseby@arkadian.com", "Upper West Side, NY"),
("54321", "Joe", "Bloggs", "0563212421", "jbloggs@gmail.com", "Jones Street, CA")
;

INSERT INTO customer VALUES
("12345", "Bruce", "Wayne", "1241125125", "bwayne@wayneenterprise.com" , "Wayne Tower, GT"),
("54321", "Will", "Smith", "1251252124", "getjiggywithit@gmail.com", "West Philadelphia, PA")
;

INSERT INTO contractor VALUES
("12345", "Lex", "Luthor", "1245125212", "lluthor@lexcorp.com", "LexCorp Tower, DE"),
("54321", "Jeff", "Jones", "1252125512", "jjones@gmail.com", "Salem, MT")
;
