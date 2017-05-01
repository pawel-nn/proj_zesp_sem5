use proj_zesp_db;

CREATE  TABLE spring_users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));
  
CREATE TABLE spring_user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES spring_users (username));

insert into spring_users (username, password, enabled) values ('adm','adm1', 1);
insert into spring_user_roles (username, role) values ('adm','ROLE_ADMIN');
insert into spring_users (username, password, enabled) values ('emp','emp1', 1);
insert into spring_user_roles (username, role) values ('emp','ROLE_EMPLOYEE');

/* Klient 1 employee 1 musz¹ istniec */
/* po odpaleniu spring jak istnieja inne tabele*/
/*
insert into client_accounts_web (username, password, enabled, session_id) values ('alama','alama', 1, null);
insert into clients (client_id, accommodation_number, city, city_post_code, email, first_name, last_name, mobile, street, username) values (1,'23', "Miato 1", "20-200", "rrt@ffr.cd", "Ala", "Ma", "6775443567", "strasse 1", "alama");
insert into employees (employee_id, accommodation_number, city, city_post_code, email, first_name, last_name, mobile, position, salary, street, username) values (1,'23', "Miato 1", "20-200", "rrt@ffr.cd", "Ada", "Kota", "1176453", "Klucznik", "1z³", "strasse 2", "emp");
insert  into cooperations (cooperation_id, date_of_last_event, type_of_cooperation, client_id, employee_id, description) values (1,null, "Sprawa s¹dowa", 1, 1, "EEEeee1");
insert  into cooperations (cooperation_id, date_of_last_event, type_of_cooperation, client_id, employee_id, description) values (2,null, "Doradztwo prawne", 1, 1, "EEEeee2");

insert into client_accounts_web (username, password, enabled) values ('ttt','ttt', 1);
insert into clients (client_id, accommodation_number, city, city_post_code, email, first_name, last_name, mobile, street, username) values (0, '23', "ttt", "20-200", "ttt@ffr.cd", "ttt", "ttt", "6775443567", "strasse 1", "ttt");

select * from clients
*/