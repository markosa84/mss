DROP TABLE Gender;
CREATE TABLE Gender(
 id INT NOT NULL AUTO_INCREMENT,
 gender VARCHAR(255) NOT NULL,
 PRIMARY KEY (id)
);
 INSERT INTO Gender (id,gender)
 VALUES (1,'male'), (2,'female'),(3, 'cannot_be_determinate'),(4, 'not_public');