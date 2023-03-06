DROP TABLE Language;
CREATE TABLE LANGUAGE(
 id INT NOT NULL AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 PRIMARY KEY (id)
);
 INSERT INTO Language (id,name)
 VALUES (1,'hungarian'), (2,'deutsch'),(3, 'english'),(4, 'chinese');