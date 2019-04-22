DELETE FROM ORDER_TAB;
DELETE FROM CITY;
DELETE FROM COUNTRY;
DELETE FROM USER;

INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Russia', 'Russian');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Ukraine', 'Ukranian');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Belarus', 'Belarussian');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('France', 'French');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Thailand', 'Thai');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Germany', 'German');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Italy', 'Italian');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Spain', 'Spanish');
INSERT INTO COUNTRY (NAME, LANGUAG) VALUES ('Kazakhstan', 'Kazakh');

--Russia----
INSERT INTO CITY (
                    COUNTRY_ID,
                    NAME,
                    POPULATION,
                    DISCRIMINATOR,
                    COLDEST_TEMP,
                    COLDEST_MONTH,
                    HOTTEST_TEMP,
                    HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Russia'),
                    'Petersburg',
                    300000,
                    'Cold',
                    22,
                    'December',
                     NULL, NULL
                   );
--Ukraine----
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Ukraine'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
--Belarus----
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Belarus'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
--France----
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'France'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
--Thailand----
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Thailand'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
INSERT INTO CITY (
                    COUNTRY_ID,
                                        NAME,
                                        POPULATION,
                                        DISCRIMINATOR,
                                        COLDEST_TEMP,
                                        COLDEST_MONTH,
                                        HOTTEST_TEMP,
                                        HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Thailand'),
                    'Petersburg',
                                        300000,
                                        'Cold',
                                        22,
                                        'December',
                                         NULL, NULL
                   );
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH,
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Thailand'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
--Italy----
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Italy'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
--Germany----
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Germany'),
                  'Petersburg',
                                      300000,
                                      'Cold',
                                      22,
                                      'December',
                                       NULL, NULL
                   );
INSERT INTO CITY (
                    COUNTRY_ID,
                                        NAME,
                                        POPULATION,
                                        DISCRIMINATOR,
                                        COLDEST_TEMP,
                                        COLDEST_MONTH,
                                        HOTTEST_TEMP,
                                        HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Germany'),
                   'Petersburg',
                                       300000,
                                       'Cold',
                                       22,
                                       'December',
                                        NULL, NULL
                   );
--Spain
INSERT INTO CITY (
                    COUNTRY_ID,
                                        NAME,
                                        POPULATION,
                                        DISCRIMINATOR,
                                        COLDEST_TEMP,
                                        COLDEST_MONTH,
                                        HOTTEST_TEMP,
                                        HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Spain'),
               'Petersburg',
                                                      300000,
                                                      'Cold',
                                                      22,
                                                      'December',
                                                       NULL, NULL
                   );
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Spain'),
                 'Petersburg',
                                                        300000,
                                                        'Cold',
                                                        22,
                                                        'December',
                                                         NULL, NULL
                   );
--Kazakhstan
INSERT INTO CITY (
                   COUNTRY_ID,
                                       NAME,
                                       POPULATION,
                                       DISCRIMINATOR,
                                       COLDEST_TEMP,
                                       COLDEST_MONTH,
                                       HOTTEST_TEMP,
                                       HOTTEST_MONTH
                    )
                    VALUES
                   (
                    (SELECT ID FROM COUNTRY WHERE NAME = 'Kazakhstan'),
                  'Petersburg',
                                                         300000,
                                                         'Cold',
                                                         22,
                                                         'December',
                                                          NULL, NULL
                   );

