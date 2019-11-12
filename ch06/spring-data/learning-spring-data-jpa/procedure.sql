DROP PROCEDURE IF EXISTS add_name_prefix;
DELIMITER $$
CREATE PROCEDURE add_name_prefix(IN name VARCHAR(255), OUT prefix_name VARCHAR(255))
BEGIN
    set prefix_name = CONCAT('Mr./Mrs. ', name);
END