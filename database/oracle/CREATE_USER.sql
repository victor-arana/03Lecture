CREATE USER LECTURE3 IDENTIFIED BY LECTURE3 DEFAULT TABLESPACE USERS 
TEMPORARY TABLESPACE TEMP;

GRANT CONNECT, RESOURCE, CREATE TABLE, CREATE SEQUENCE TO LECTURE3;