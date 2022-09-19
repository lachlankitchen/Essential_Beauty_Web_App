CREATE TABLE IF NOT EXISTS PRODUCT (
PRODUCTID varchar(10) unique,
NAME varchar(50) not null,
DESCRIPTION varchar(200) not null,
CATEGORY varchar(50) not null,
LISTPRICE numeric(5,2) not null,
QUANTITYINSTOCK double not null,
constraint product_PK primary key (PRODUCTID)
);


CREATE TABLE IF NOT EXISTS CUSTOMER (
Customer_ID INTEGER AUTO_INCREMENT (1000) unique,
USERNAME varchar(50) unique not null,
FIRSTNAME varchar(50) not null,
SURNAME varchar(50) not null,
PASSWORD varchar(50) not null,
EMAILADDRESS varchar(50) not null,
SHIPPINGADDRESS varchar(50) not null,
constraint customer_PK primary key (Customer_ID)
);