CREATE TABLE IF NOT EXISTS third_person(
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  age int(11) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO third_person (name, age) VALUES ('wyf', 35);
INSERT INTO third_person (name, age) VALUES ('foo', 34);
INSERT INTO third_person (name, age) VALUES ('bar', 33);
INSERT INTO third_person (name, age) VALUES ('www', 32);