
-- language data
-----------------
INSERT INTO Languages (id,lang)
VALUES (1,'hungarian'), (2,'deutsch'),(3, 'english'),(4, 'chinese');

--gender data
---------------
INSERT INTO Gender(id,gender)
 VALUES (1,'male'), (2,'female'),(3, 'cannot_be_determinate'),(4, 'not_public'),(5, 'like_Lajos');

-- doktor data
---------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
  LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
   MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
  VALUES ('Doctor', 'false'
  ,'doki@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Csaba'
  , 'Rác','1970-02-18', 'Szabadka',
  'Rajmund Ilona', (SELECT id FROM GENDER WHERE gender='male'),
   '+36706380258','ROLE_DOCTOR');

 INSERT INTO MSS_USER (DTYPE, ACTIVE,  EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
    DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, GENDER,
     PHONE_NUMBER, ROLES)
     VALUES ('Doctor', 'false', 'dokk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Földi',
     '1967-09-18', 'Szeged', 'Rák Ilona',  (SELECT id FROM GENDER WHERE gender='male'),
      '+36306415258', 'ROLE_DOCTOR');

--client data
--------------
INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
 DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
  PHONE_NUMBER,REGISTRATION_DATE, ROLES)
  VALUES ('Client','false', 'aleeeai@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Árpi', 'Balasa',
  '1970-02-18', 'Budapest', 'Pátrik Izolda', '12345118911', (SELECT id FROM GENDER WHERE gender='like_Lajos'),
   '+36706389858','2023-02-20 10:10:14','ROLE_CLIENT');

   INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
       DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
        PHONE_NUMBER,REGISTRATION_DATE, ROLES)
        VALUES ('Client', 'false', 'dottk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Berkes',
        '1967-01-18', 'Győr', 'Andula Erika', '11881256789', (SELECT id FROM GENDER WHERE gender='female'),
         '+36306410058',CURRENT_TIME,'ROLE_CLIENT');

 --mssUser-languages (client_user_id languages_id)
---------------------------------------------------

 --asssistant data
------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
  LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
   MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
  VALUES ('Assistant', 'false'
  ,'assistant1@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Erika'
  , 'Vörös','1978-06-28', 'Baja',
  'Andula Hajnalka', (SELECT id FROM GENDER WHERE gender='female'),
   '+36306382258','ROLE_ASSISTANT' );

--admin data
-------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
  LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
   MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
  VALUES ('Admin', 'false'
  ,'admin111@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Csaba'
  , 'f0ldi','1970-02-18', 'Csantavér',
  'Horváth Ilona', (SELECT id FROM GENDER WHERE gender='male'),
   '+36706110258','ROLE_ADMIN' );

   --lang (languages_id mss_user_id)
    -----------------------------------
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (1,1);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (1,3);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (2,2);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (2,4);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (3,3);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (4,4);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (2,1);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (1,5);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (2,5);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (3,5);
    INSERT INTO LANG (languages_id,mss_user_id) VALUES (4,5);

