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

CREATE TABLE IF NOT EXISTS SALE (
    SALE_ID INTEGER AUTO_INCREMENT (1000) unique,
    SALE_DATE DATE,
    Customer_ID INTEGER not null,
    STATUS varchar(10) not null,
    constraint sale_PK primary key (SALE_ID),
    constraint sale_FK foreign key (Customer_ID) references CUSTOMER
);

CREATE TABLE IF NOT EXISTS SALEITEM (
    PRODUCTID varchar(10) not null,
    QUANTITYPURCHASED integer not null,
    SALEPRICE numeric(5,2) not null,
    SALE_ID INTEGER,
    constraint saleItem_sale_FK foreign key (SALE_ID) references SALE,
    constraint saleItem_product_FK foreign key (PRODUCTID) references PRODUCT,
    constraint saleItem_PK primary key (SALE_ID, PRODUCTID)
);


