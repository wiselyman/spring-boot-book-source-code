ALTER TABLE third_person add COLUMN city varchar(255) NOT NULL;

UPDATE third_person set city = 'hefei';