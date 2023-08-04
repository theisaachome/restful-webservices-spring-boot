INSERT INTO users(id,name,username,email,password) VALUES (1,'Isaac','isaachome','isaachome@gmail.com','123456');
INSERT INTO users(id,name,username,email,password) VALUES (2,'ADMIN','admin','admin.highway@gmail.com','123456');
INSERT INTO users(id,name,username,email,password) VALUES (3,'Mercy Home','mercyhome','mercyhome@gmail.com','$2a$10$A95KzY8E4duYWgpX4iMWr.krVrmqOOepSh9cJXQt1IpLdf3y8iLj6');


INSERT INTO roles(id,name) VALUES (1,'ROLE_USER');
INSERT INTO roles(id,name) VALUES (2,'ROLE_ADMIN');

INSERT INTO user_roles(user_id,role_id) VALUES(1,1);
INSERT INTO user_roles(user_id,role_id) VALUES(2,2);
