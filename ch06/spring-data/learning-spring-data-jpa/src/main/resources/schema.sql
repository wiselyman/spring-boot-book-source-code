CREATE TABLE IF NOT EXISTS another_person(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  age int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);