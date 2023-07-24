INSERT INTO users(id,name,username,email,password) VALUES (1,'Isaac','isaachome','isaachome@gmail.com','123456');
INSERT INTO users(id,name,username,email,password) VALUES (2,'ADMIN','admin','admin.highway@gmail.com','123456');


INSERT INTO roles(id,name) VALUES (1,'ROLE_USER');
INSERT INTO roles(id,name) VALUES (2,'ROLE_ADMIN');

INSERT INTO user_roles(user_id,role_id) VALUES(1,1);
INSERT INTO user_roles(user_id,role_id) VALUES(2,2);
