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

select * from spring_users