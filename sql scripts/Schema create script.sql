-- creating user, schema and granting permission
CREATE USER 'odin'@'localhost' IDENTIFIED BY 'odin';
ALTER USER 'odin'@'localhost' PASSWORD EXPIRE NEVER;
CREATE SCHEMA `odin` CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL ON `odin`.* TO `odin`@'localhost';


