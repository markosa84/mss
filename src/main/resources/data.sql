
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
INSERT INTO Appointment_status(status_id,status)
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
    VALUES ('Doctor', 'true'
    , 'dokk@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Zoli', 'Ozsvár',
     (SELECT gender_id FROM GENDER WHERE gender='Male'),'2023-01-02 08:15:14.587231',
     '+36306415258', 'ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
 VALUES ('Doctor', 'true'
 ,'doki1@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Imre','Okos'
 , (SELECT gender_id FROM GENDER WHERE gender='Male'),'2022-12-15 10:15:28.145238','+36706383358','ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
 VALUES ('Doctor', 'true'
 ,'doki12@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Árpád','Gera'
 , (SELECT gender_id FROM GENDER WHERE gender='Male'),'2022-11-05 10:15:28.145238','+36706777258','ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
 VALUES ('Doctor', 'true'
 ,'doki1as@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Víg','Béla'
 , (SELECT gender_id FROM GENDER WHERE gender='Male'),'2022-12-21 10:15:28.145238','+36706355458','ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE,
 EMAIL, PASSWORD, FIRST_NAME,
 LAST_NAME,GENDER,REGISTRATION_DATE,
  PHONE_NUMBER, ROLES)
 VALUES ('Doctor', 'true'
 ,'dokkbfa@gmail.com', '1151371fe13913e1f31121181f51a311819311e1721881ea', 'Tamás','Béres'
 , (SELECT gender_id FROM GENDER WHERE gender='Male'),'2023-10-24 10:15:28.145238','+36706381111','ROLE_DOCTOR');

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
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (1,'Urologist','Az urológia mindkét nemnél a szervezet méregtelenítésében fontos szerepet játszó vizelet elválasztó és elvezető rendszer , valamint a férfi nemi szervek megbetegedéseivel foglalkozó szakterület.');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (2,'Psychologist','A mai modern felfogásunk szerint a pszichológia az emberi gondolkozás, viselkedés és érzelmi élet összefüggéseit vizsgáló tudományterület.');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (3,'Surgeon','A sebészet megbetegedéseinek műtéti kezelését végzi,');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (4,'Dentist','A fogászat a szájüreg és a rágókészülék (rágóizmok, rágóízület, fogak) betegségeinek gyógyítása.');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (5,'Gynecologist','Nőgyógyászati szakrendelést nem csak panasz esetén, hanem szűrés céljából is ajánlott felkeresni.');


-- mss_user_to_areaOfExpertise( user_id,areaOfExpertise_id)
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (1 ,1);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (1 ,3);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (2 ,2);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (2 ,4);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (3 ,5);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (3 ,1);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (4 ,3);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (4 ,4);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (5 ,2);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (5 ,4);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (6 ,5);
INSERT INTO mss_user_to_area_of_expertise ( user_id ,  area_of_expertise_id) VALUES (6 ,2);


-- dayofweek data
INSERT INTO day_of_week (day_of_week_id,day_name)
VALUES (1,'Monday'), (2,'Tuesday'), (3,'Wednesday'), (4,'Thursday'), (5,'Friday'), (6,'Saturday'), (7,'Sunday');

-- weekly workout
--docotr_id = 1
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (1, '09:00:00', '12:00:00', 1, 1, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (2, '09:00:00', '16:00:00', 1, 2, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (3, '09:00:00', '12:00:00', 3, 3, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (4, '09:00:00', '12:00:00', 3, 4, 1);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (5, '07:00:00', '16:00:00', 3, 5, 1);
--doctor_id  = 2
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (6, '09:00:00', '12:00:00', 2, 1, 2);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (7, '09:00:00', '16:00:00', 4, 2, 2);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (8, '09:00:00', '12:00:00', 2, 3, 2);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (9, '09:00:00', '12:00:00', 4, 4, 2);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (10, '09:00:00', '16:00:00', 2, 5, 2);
--doctor_id = 3
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (11, '09:00:00', '12:00:00', 5, 1, 3);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (12, '09:00:00', '16:00:00', 5, 2, 3);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (13, '09:00:00', '12:00:00', 5, 3, 3);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (14, '09:00:00', '12:00:00', 1, 4, 3);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (15, '09:00:00', '16:00:00', 1, 5, 3);
--doctor_id  = 4
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (16, '09:00:00', '12:00:00', 3, 1, 4);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (17, '09:00:00', '16:00:00', 3, 2, 4);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (18, '09:00', '12:00:00', 3, 3, 4);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (19, '09:00:00', '12:00:00', 4, 4, 4);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (20, '09:00:00', '16:00:00', 4, 5, 4);
--docotr_id = 5
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (21, '09:00:00', '12:00:00', 4, 1, 5);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (22, '09:00:00', '16:00:00', 2, 2, 5);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (23, '09:00:00', '12:00:00', 2, 3, 5);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (24, '09:00:00', '12:00:00', 2, 4, 5);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (25, '09:00:00', '16:00:00', 4, 5, 5);
--docotr_id = 6
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (26, '09:00:00', '12:00:00', 5, 1, 6);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (27, '09:00:00', '16:00:00', 5, 2, 6);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (28, '09:00:00', '12:00:00', 5, 3, 6);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (29, '9:00:00', '12:00:00', 2, 4, 6);
INSERT INTO doctors_working_hours (working_hours_day_id ,start_time ,end_time ,area_id ,day_of_week_id ,doctor_id)
VALUES (30, '09:00:00', '16:00:00', 2, 5, 6);


--appointment proba

--INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
--VALUES (CONCAT(DATEADD('DAY', 1, CURRENT_DATE), ' 09:15:00'), CONCAT(DATEADD('DAY', 1, CURRENT_DATE), ' 09:00:00'), 3, 7, 1, 1);

--INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
--VALUES (CONCAT(DATEADD('DAY', 0, CURRENT_DATE), ' 10:15:00'), CONCAT(DATEADD('DAY', 0, CURRENT_DATE), ' 10:00:00'), 2, 8, 5, 1);

--INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
--VALUES (CONCAT(DATEADD('DAY', 13, CURRENT_DATE), ' 11:15:00'), CONCAT(DATEADD('DAY', 13, CURRENT_DATE), ' 11:00:00'), 3, 9, 2, 1);

--INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
--VALUES (CONCAT(DATEADD('DAY', 1, CURRENT_DATE), ' 11:30:00'), CONCAT(DATEADD('DAY', 1, CURRENT_DATE), ' 11:15:00'), 3, 10, 2, 1);

--INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
--VALUES (CONCAT(DATEADD('DAY', 3, CURRENT_DATE), ' 11:15:00'), CONCAT(DATEADD('DAY', 3, CURRENT_DATE), ' 11:00:00'), 4,8, 5, 1);

--appointment proba v.2
INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES (
  CONCAT(
    DATEADD('DAY', CASE
        WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 10  END, CURRENT_DATE),
    ' 09:15:00' ),
  CONCAT(
    DATEADD('DAY', CASE
        WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 10 END,CURRENT_DATE),
    ' 09:00:00'),3, 7, 1, 1);

INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES (
  CONCAT(
    DATEADD('DAY', CASE
        WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 9  END, CURRENT_DATE),
    ' 11:15:00' ),
  CONCAT(
    DATEADD('DAY', CASE
        WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 9 END,CURRENT_DATE),
    ' 11:00:00'),3, 7, 1, 1);

 INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
 VALUES (
   CONCAT(
     DATEADD('DAY', CASE
         WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 10  END, CURRENT_DATE),
     ' 10:15:00' ),
   CONCAT(
     DATEADD('DAY', CASE
         WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 10 END,CURRENT_DATE),
     ' 10:00:00'),2, 8, 5, 1);

INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES (
  CONCAT(
    DATEADD('DAY', CASE
        WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 9  END, CURRENT_DATE),
    ' 13:15:00' ),
  CONCAT(
    DATEADD('DAY', CASE
        WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 9 END,CURRENT_DATE),
    ' 13:00:00'),3, 10, 4, 1);

    INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
    VALUES (
      CONCAT(
        DATEADD('DAY', CASE
            WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 5  END, CURRENT_DATE),
        ' 13:45:00' ),
      CONCAT(
        DATEADD('DAY', CASE
            WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 5 END,CURRENT_DATE),
        ' 13:30:00'),3, 8, 4, 1);

    INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
    VALUES (
      CONCAT(
        DATEADD('DAY', CASE
            WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 5  END, CURRENT_DATE),
        ' 14:45:00' ),
      CONCAT(
        DATEADD('DAY', CASE
            WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 5 END,CURRENT_DATE),
        ' 14:30:00'),4, 8, 4, 1);

    INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
     VALUES (
        CONCAT(
          DATEADD('DAY', CASE
             WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 5  END, CURRENT_DATE),
          ' 15:45:00' ),
        CONCAT(
          DATEADD('DAY', CASE
             WHEN DAY_OF_WEEK(DATEADD('DAY', 9, CURRENT_DATE)) IN (6, 7) THEN 12 ELSE 5 END,CURRENT_DATE),
          ' 15:30:00'),4, 10, 4, 1);

     INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
       VALUES (
        CONCAT(
          DATEADD('DAY', CASE
            WHEN DAY_OF_WEEK(DATEADD('DAY', 1, CURRENT_DATE)) IN (6, 7) THEN 3 ELSE 5  END, CURRENT_DATE),
        ' 16:45:00' ),
        CONCAT(
          DATEADD('DAY', CASE
            WHEN DAY_OF_WEEK(DATEADD('DAY', 1, CURRENT_DATE)) IN (6, 7) THEN 3 ELSE 5 END,CURRENT_DATE),
        ' 16:30:00'),4, 7, 5, 1);


      INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
        VALUES (
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 1, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6  END, CURRENT_DATE),
             ' 11:45:00' ),
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 1, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6 END,CURRENT_DATE),
                   ' 11:30:00'),3, 11, 1, 1);
  INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
        VALUES (
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 1, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6  END, CURRENT_DATE),
             ' 09:45:00' ),
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 1, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6 END,CURRENT_DATE),
                   ' 09:30:00'),3, 6, 4, 1);
  INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
        VALUES (
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 3, CURRENT_DATE)) IN (6, 7) THEN 3 ELSE 6  END, CURRENT_DATE),
             ' 11:45:00' ),
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 3, CURRENT_DATE)) IN (6, 7) THEN 3 ELSE 6 END,CURRENT_DATE),
                   ' 11:30:00'),2, 6, 5, 1);

  INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
        VALUES (
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 0, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6  END, CURRENT_DATE),
             ' 11:45:00' ),
           CONCAT(
             DATEADD('DAY', CASE
                WHEN DAY_OF_WEEK(DATEADD('DAY', 0, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6 END,CURRENT_DATE),
                   ' 11:30:00'),2, 10, 6, 1);

  INSERT INTO appointment (end_date, start_date, area_of_expertise_id, client_id, doctor_id, status_id)
        VALUES (
         CONCAT(
             DATEADD('DAY', CASE
             WHEN DAY_OF_WEEK(DATEADD('DAY', 0, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6  END, CURRENT_DATE),
           ' 12:45:00' ),
          CONCAT(
            DATEADD('DAY', CASE
               WHEN DAY_OF_WEEK(DATEADD('DAY', 0, CURRENT_DATE)) IN (6, 7) THEN 4 ELSE 6 END,CURRENT_DATE),
                 ' 12:30:00'),2, 10, 6, 1);
