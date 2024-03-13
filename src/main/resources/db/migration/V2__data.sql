
---- Language data
-------------------
INSERT INTO Language (language_id,name)
    VALUES (1,'Hungarian'), (2,'German'),(3, 'English'),(4, 'Chinese');

---- Gender data
-----------------
INSERT INTO Gender(gender_id,gender)
    VALUES (1,'Male'), (2,'Female'), (3,'Other');

---- AppointmentStatus data
-----------------
INSERT INTO Appointment_status(status_id,status)
    VALUES (1,'Completed'), (2,'In progress'), (3,'Booked'),(4,'Did not come'),(5,'Canceled');

----Doctor data
-----------------
INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,GENDER, REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Doctor',
            'true',
            'doki@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Csaba',
            'Földi',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2022-12-25 10:15:28.145238',
            '+36706380258',
            'ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, GENDER, REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Doctor',
            'true',
            'dokk@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Zoli',
            'Ozsvár',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2023-01-02 08:15:14.587231',
            '+36306415258',
            'ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Doctor',
            'true',
            'doki1@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Imre',
            'Okos',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2022-12-15 10:15:28.145238',
            '+36706383358',
            'ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Doctor',
            'true',
            'doki12@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Árpád',
            'Gera',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2022-11-05 10:15:28.145238',
            '+36706777258',
            'ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME,GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Doctor',
            'true',
            'doki1as@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Víg',
            'Béla',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2022-12-21 10:15:28.145238',
            '+36706355458',
            'ROLE_DOCTOR');

INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Doctor',
            'true',
            'dokkbfa@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Tamás',
            'Béres',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2023-10-24 10:15:28.145238',
            '+36706381111',
            'ROLE_DOCTOR');

---- Client data
----------------
INSERT INTO MSS_USER (
    DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH,
    PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER, PHONE_NUMBER,REGISTRATION_DATE, ROLES)
        VALUES ('Client',
                'true',
                'berni@gmail.com',
                '1151371fe13913e1f31121181f51a311819311e1721881ea',
                'Bernadett',
                'Pajor',
                '1970-02-18',
                'Budapest',
                'Pátrik Izolda',
                '123-451-189',
                (SELECT gender_id FROM GENDER WHERE gender='Male'),
                '+36706389858','2023-02-20 10:10:14.254998',
                'ROLE_CLIENT');

INSERT INTO MSS_USER (
    DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH,
    PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER, PHONE_NUMBER,REGISTRATION_DATE, ROLES)
        VALUES ('Client',
                'true',
                'aleeeai@gmail.com',
                '1151371fe13913e1f31121181f51a311819311e1721881ea',
                'Árpi',
                'Balasa',
                '1970-02-18',
                'Budapest',
                'Pátrik Izolda',
                '123-451-189',
                (SELECT gender_id FROM GENDER WHERE gender='Male'),
                '+36706389858','2023-02-20 10:10:14.254998',
                'ROLE_CLIENT');

INSERT INTO MSS_USER (
    DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH,
    PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER, PHONE_NUMBER,REGISTRATION_DATE, ROLES)
        VALUES ('Client',
                'false',
                'kovpis@gmail.com',
                '1151371fe13913e1f31121181f51a311819311e1721881ea',
                'Pista',
                'Kovács',
                '1970-02-18',
                'Budapest',
                'Pátrik Izolda',
                '123-451-189',
                (SELECT gender_id FROM GENDER WHERE gender='Male'),
                '+36706389858','2023-02-20 10:10:14.254998',
                'ROLE_CLIENT');

INSERT INTO MSS_USER (
    DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH,
    PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER, PHONE_NUMBER,REGISTRATION_DATE, ROLES)
        VALUES ('Client',
                'true',
                'kovalb@gmail.com',
                '1151371fe13913e1f31121181f51a311819311e1721881ea',
                'Albert',
                'Kovács',
                '1970-02-18',
                'Budapest',
                'Pátrik Izolda',
                '123-451-189',
                (SELECT gender_id FROM GENDER WHERE gender='Male'),
                '+36706389858','2023-02-20 10:10:14.254998',
                'ROLE_CLIENT');

INSERT INTO MSS_USER (
    DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH,
    PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER, PHONE_NUMBER, REGISTRATION_DATE, ROLES)
        VALUES ('Client',
                'false',
                'dottk@gmail.com',
                '1151371fe13913e1f31121181f51a311819311e1721881ea',
                'Zoli',
                'Berkes',
                '1967-01-18',
                'Győr',
                'Andula Erika',
                '11881256789',
                (SELECT gender_id FROM GENDER WHERE gender='Female'),
                '+36306410058',
                CURRENT_TIMESTAMP,
                'ROLE_CLIENT');

---- Assistant data
--------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Assistant',
            'true',
            'assistant1@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Erika',
            'Vörös',
            (SELECT gender_id FROM GENDER WHERE gender='Female'),
            '2022-05-03 10:10:10.459321',
            '+36306382258',
            'ROLE_ASSISTANT');

---- Admin data
---------------
INSERT INTO MSS_USER (DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('Admin',
            'true',
            'admin111@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Góra',
            'Gábor',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2021-05-25 07:10:15.569823',
            '+36706110258',
            'ROLE_ADMIN');

---- FinancialColleague data
-----------------------
INSERT INTO MSS_USER (DTYPE, ACTIVE,  EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, GENDER,REGISTRATION_DATE, PHONE_NUMBER, ROLES)
    VALUES ('FinancialColleague',
            'true',
            'financ131@gmail.com',
            '1151371fe13913e1f31121181f51a311819311e1721881ea',
            'Árpád',
            'Gyurita',
            (SELECT gender_id FROM GENDER WHERE gender='Male'),
            '2021-05-25 14:15:01.458712',
            '+36306110228',
            'ROLE_FINANCIALCOLLEAGUE');


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
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (1,'Urologist','Urology is a part of health care that deals with diseases of the male and female urinary tract (kidneys, ureters, bladder and urethra). It also deals with the male organs of reproduction.');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (2,'Psychologist','According to our modern understanding, psychology is a scientific field that studies the connections between human thinking, behavior and emotions.');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (3,'Surgeon','Surgery carries out the surgical treatment of diseases.');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (4,'Dentist','Dentistry is the treatment of diseases of the oral cavity and the masticatory apparatus (chewing muscles, chewing joint, teeth).');
INSERT INTO area_of_expertise (area_of_expertise_id,name,description) VALUES (5,'Gynecologist','It is recommended to visit a gynecology clinic not only in case of complaints, but also for screening purposes.');

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

-- weekly working hours
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

---- Appointments: March 18 - 25
-----------------------------------
INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
    VALUES  (
	    '2024-03-18 14:00',
	    '2024-03-18 14:15',
	    (SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Surgeon'),
	    (SELECT user_id FROM mss_user WHERE email = 'berni@gmail.com'),
	    (SELECT user_id FROM mss_user WHERE email = 'doki@gmail.com'),
	    (SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-19 09:00',
	'2024-03-19 09:15',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Surgeon'),
	(SELECT user_id FROM mss_user WHERE email = 'berni@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-20 09:00',
	'2024-03-20 09:15',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Psychologist'),
	(SELECT user_id FROM mss_user WHERE email = 'aleeeai@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki1as@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-21 09:00',
	'2024-03-21 09:15',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Surgeon'),
	(SELECT user_id FROM mss_user WHERE email = 'kovalb@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki12@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-21 10:00',
	'2024-03-21 10:15',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Surgeon'),
	(SELECT user_id FROM mss_user WHERE email = 'aleeeai@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki12@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-21 11:30',
	'2024-03-21 11:45',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Dentist'),
	(SELECT user_id FROM mss_user WHERE email = 'aleeeai@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki12@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-21 15:00',
	'2024-03-21 15:15',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Dentist'),
	(SELECT user_id FROM mss_user WHERE email = 'kovalb@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki12@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-22 11:45',
	'2024-03-22 12:00',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Dentist'),
	(SELECT user_id FROM mss_user WHERE email = 'berni@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki1as@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-22 10:15',
	'2024-03-22 10:30',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Surgeon'),
	(SELECT user_id FROM mss_user WHERE email = 'dottk@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-25 10:15',
	'2024-03-25 10:30',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Surgeon'),
	(SELECT user_id FROM mss_user WHERE email = 'kovalb@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki12@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-25 14:15',
	'2024-03-25 14:30',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Psychologist'),
	(SELECT user_id FROM mss_user WHERE email = 'kovpis@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'doki1as@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-25 15:15',
	'2024-03-25 15:30',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Psychologist'),
	(SELECT user_id FROM mss_user WHERE email = 'kovalb@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'dokkbfa@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));

INSERT INTO appointment (start_date, end_date, area_of_expertise_id, client_id, doctor_id, status_id)
VALUES  (
	'2024-03-25 16:15',
	'2024-03-25 16:30',
	(SELECT area_of_expertise_id FROM area_of_expertise WHERE  name = 'Psychologist'),
	(SELECT user_id FROM mss_user WHERE email = 'kovalb@gmail.com'),
	(SELECT user_id FROM mss_user WHERE email = 'dokkbfa@gmail.com'),
	(SELECT status_id FROM appointment_status WHERE status = 'Booked'));