INSERT INTO MSS_USER (
    DTYPE, ACTIVE, EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DATE_OF_BIRTH,
    PLACE_OF_BIRTH, MOTHERS_NAME, TAJNUMBER, GENDER, PHONE_NUMBER, REGISTRATION_DATE, ROLES)
        VALUES ('Client',
                'true',
                'munka6502@gmail.com',
                '1151371fe13913e1f31121181f51a311819311e1721881ea',
                'Földi',
                'Berkes',
                '1967-07-18',
                'Szeged',
                'Andula Erika',
                '118-712-517',
                (SELECT gender_id FROM GENDER WHERE gender='Female'),
                '+36306415058',
                CURRENT_TIMESTAMP,
                'ROLE_CLIENT');
