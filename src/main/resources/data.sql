
---- language data
-------------------
INSERT INTO Language (language_id,name)
VALUES (1,'Hungarian'), (2,'Deutsch'),(3, 'English'),(4, 'Chinese');

----gender data
-----------------
INSERT INTO Gender(gender_id,gender)
 VALUES (1,'Male'), (2,'Female'),(3,'Other');

----ApoointmentStatus data
-----------------
INSERT INTO AppointmentStatus(status_id,status)
 VALUES (1,'Completed'), (2,'In progress'),(3,'Booked'),(4,'Did not come'),(5,'Canceled it');

---- doktor data
-----------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
 VALUES ('Doctor', 'true'
 ,'doki@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Csaba','Földi'
 , (SELECT gender_id FROM GENDER WHERE gender='Male'),'2022-12-25 10:15:28.145238','+36706380258','ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE,  EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
    GENDER,REGISTRATION_DATE,
     PHONE_NUMBER, ROLES)
    VALUES ('Doctor', 'true', 'dokk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Ozsvár',
     (SELECT gender_id FROM GENDER WHERE gender='Male'),'2023-01-02 08:15:14.587231',
     '+36306415258', 'ROLE_DOCTOR');

----client data
----------------
   INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
    DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
     PHONE_NUMBER,REGISTRATION_DATE, ROLES)
     VALUES ('Client','true', 'berni@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Bernadett', 'Pajor',
     '1970-02-18', 'Budapest', 'Pátrik Izolda', '123-451-189', (SELECT gender_id FROM GENDER WHERE gender='Male'),
      '+36706389858','2023-02-20 10:10:14.254998','ROLE_CLIENT');

INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
 DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
  PHONE_NUMBER,REGISTRATION_DATE, ROLES)
  VALUES ('Client','true', 'aleeeai@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Árpi', 'Balasa',
  '1970-02-18', 'Budapest', 'Pátrik Izolda', '123-451-189', (SELECT gender_id FROM GENDER WHERE gender='Male'),
   '+36706389858','2023-02-20 10:10:14.254998','ROLE_CLIENT');

   INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
    DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
     PHONE_NUMBER,REGISTRATION_DATE, ROLES)
     VALUES ('Client','false', 'kovpis@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Pista', 'Kovács',
     '1970-02-18', 'Budapest', 'Pátrik Izolda', '123-451-189', (SELECT gender_id FROM GENDER WHERE gender='Male'),
      '+36706389858','2023-02-20 10:10:14.254998','ROLE_CLIENT');

   INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
    DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
     PHONE_NUMBER,REGISTRATION_DATE, ROLES)
     VALUES ('Client','true', 'kovalb@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Albert', 'Kovács',
     '1970-02-18', 'Budapest', 'Pátrik Izolda', '123-451-189', (SELECT gender_id FROM GENDER WHERE gender='Male'),
      '+36706389858','2023-02-20 10:10:14.254998','ROLE_CLIENT');

   INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,
      DATE_OF_BIRTH, PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER,
       PHONE_NUMBER,REGISTRATION_DATE, ROLES)
       VALUES ('Client', 'false', 'dottk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Berkes',
       '1967-01-18', 'Győr', 'Andula Erika', '11881256789', (SELECT gender_id FROM GENDER WHERE gender='Female'),
         '+36306410058',CURRENT_TIMESTAMP,'ROLE_CLIENT');

--asssistant data
--------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
  LAST_NAME,GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
  VALUES ('Assistant', 'true'
  ,'assistant1@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Erika'
  , 'Vörös', (SELECT gender_id FROM GENDER WHERE gender='Female'),'2022-05-03 10:10:10.459321',
   '+36306382258','ROLE_ASSISTANT' );

----admin data
---------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME, GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
  VALUES ('Admin', 'true'
  ,'admin111@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea','Góra','Gábor',
  (SELECT gender_id FROM GENDER WHERE gender='Male'),'2021-05-25 07:10:15.569823',
   '+36706110258','ROLE_ADMIN' );

-----FinancialColleague
-----------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME, GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
  VALUES ('FinancialColleague', 'true'
  ,'financ131@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Árpád', 'Gyurita',
  (SELECT gender_id FROM GENDER WHERE gender='Male'),'2021-05-25 14:15:01.458712',
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
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification ,basic_information) VALUES (1,'Urologist','Az urológia mindkét nemnél a szervezet méregtelenítésében fontos szerepet játszó vizelet elválasztó és elvezető rendszer , valamint a férfi nemi szervek megbetegedéseivel foglalkozó szakterület.');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification ,basic_information) VALUES (2,'Psychologist','A mai modern felfogásunk szerint a pszichológia az emberi gondolkozás, viselkedés és érzelmi élet összefüggéseit vizsgáló tudományterület.');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification ,basic_information) VALUES (3,'Surgeon','A sebészet megbetegedéseinek műtéti kezelését végzi,');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification ,basic_information) VALUES (4,'Dentist','A fogászat a szájüreg és a rágókészülék (rágóizmok, rágóízület, fogak) betegségeinek gyógyítása.');
INSERT INTO area_of_expertise (area_of_expertise_id ,qualification ,basic_information) VALUES (5,'Gynecologist','Nőgyógyászati szakrendelést nem csak panasz esetén, hanem szűrés céljából is ajánlott felkeresni.');


-- mss_user_to_areaOfExpertise( user_id,areaOfExpertise_id)
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (1 ,1);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (1 ,3);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (2 ,2);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (5 ,1);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (5 ,2);

-- dayofweek data
INSERT INTO day_of_week (day_of_week_id,day_name)
VALUES (1,'Monday'), (2,'Tuesday'), (3,'Wednesday'), (4,'Thursday'), (5,'Friday'), (6,'Saturday'), (7,'Sunday');


INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (1, '09:00:00', '12:00:00', 3, 1, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (2, '09:00:00', '16:00:00', 3, 2, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (3, '09:00:00', '12:00:00', 3, 3, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (4, '09:00:00', '12:00:00', 3, 4, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (5, '09:00:00', '16:00:00', 3, 5, 1);


INSERT INTO appointment (appointment_id ,end_date ,start_date ,area_of_expertise_id ,client_id ,doctor_id ,status_id)
VALUES (1,'2023-01-25 09:15:00.569823', '2023-01-25 09:00:00.569823',3,3,1,1)