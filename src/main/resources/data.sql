
---- language data
-------------------
INSERT INTO Language (language_id,name)
VALUES (1,'Hungarian'), (2,'Deutsch'),(3, 'English'),(4, 'Chinese');

----gender data
-----------------
INSERT INTO Gender(gender_id,gender)
 VALUES (1,'Male'), (2,'Female'),(3,'Other');

---- doktor data
-----------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
  MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
 VALUES ('Doctor', 'false'
 ,'doki@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Csaba'
 , 'Rác','1970-02-18', 'Szabadka',
 'Rajmund Ilona', (SELECT gender_id FROM GENDER WHERE gender='Male'),
  '+36706380258','ROLE_DOCTOR');

 INSERT INTO MSS_USER (DTYPE, ACTIVE,  EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
   DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, GENDER,
     PHONE_NUMBER, ROLES)
    VALUES ('Doctor', 'false', 'dokk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Földi',
    '1967-09-18', 'Szeged', 'Rák Ilona',  (SELECT gender_id FROM GENDER WHERE gender='Male'),
     '+36306415258', 'ROLE_DOCTOR');

----client data
----------------
INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
 DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
  PHONE_NUMBER,REGISTRATION_DATE, ROLES)
  VALUES ('Client','false', 'aleeeai@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Árpi', 'Balasa',
  '1970-02-18', 'Budapest', 'Pátrik Izolda', '12345118911', (SELECT gender_id FROM GENDER WHERE gender='Male'),
   '+36706389858','2023-02-20 10:10:14','ROLE_CLIENT');

   INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
      DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
       PHONE_NUMBER,REGISTRATION_DATE, ROLES)
       VALUES ('Client', 'false', 'dottk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Berkes',
       '1967-01-18', 'Győr', 'Andula Erika', '11881256789', (SELECT gender_id FROM GENDER WHERE gender='Female'),
         '+36306410058',CURRENT_TIME,'ROLE_CLIENT');

--asssistant data
--------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
  LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
   MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
  VALUES ('Assistant', 'false'
  ,'assistant1@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Erika'
  , 'Vörös','1978-06-28', 'Baja',
  'Andula Hajnalka', (SELECT gender_id FROM GENDER WHERE gender='Female'),
   '+36306382258','ROLE_ASSISTANT' );

----admin data
---------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
   MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
  VALUES ('Admin', 'false'
  ,'admin111@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Csaba'
  , 'Földi','1970-02-18', 'Csantavér',
  'Horváth Ilona', (SELECT gender_id FROM GENDER WHERE gender='Male'),
   '+36706110258','ROLE_ADMIN' );

-----FinancialColleague
-----------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,DATE_OF_BIRTH, PLACE_OF_BIRTH,
   MOTHERS_NAME, GENDER,
  PHONE_NUMBER, ROLES)
  VALUES ('FinancialColleague', 'false'
  ,'financ131@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Árpád'
  , 'Gyurita','1980-06-28', 'Nagyfény',
  'Kovács Gyöngyi', (SELECT gender_id FROM GENDER WHERE gender='Male'),
   '+36306110228','ROLE_FINANCIALCOLLEAGUE' );


  --lang (user_id ,languages_id)
    -----------------------------------
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (1,1);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (1,2);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (2,1);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (2,3);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (2,4);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (3,1);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (3,4);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (4,1);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (4,2);
   INSERT INTO mss_user_to_language (user_id ,language_id) VALUES (4,4);

-- areaOfExpertise (areaOfExpertise_id, qualification)
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification) VALUES (1,'urologist');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification) VALUES (2,'psychologist');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification) VALUES (3,'surgeon');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification) VALUES (4,'dentist');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification) VALUES (5,'gynecologist');


-- mss_user_to_areaOfExpertise( user_id,areaOfExpertise_id)
INSERT INTO mss_user_to_area_of_expertise ( user_id , area_of_expertise_id) VALUES (1 ,1);
INSERT INTO mss_user_to_area_of_expertise ( user_id , area_of_expertise_id) VALUES (1 ,3);
INSERT INTO mss_user_to_area_of_expertise ( user_id , area_of_expertise_id) VALUES (2 ,2);
INSERT INTO mss_user_to_area_of_expertise ( user_id , area_of_expertise_id) VALUES (5 ,1);
INSERT INTO mss_user_to_area_of_expertise ( user_id , area_of_expertise_id) VALUES (5 ,2);
