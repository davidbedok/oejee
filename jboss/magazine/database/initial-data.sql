INSERT INTO role (role_id, role_name) VALUES (0, 'oe-user');
INSERT INTO role (role_id, role_name) VALUES (1, 'oe-admin');

SELECT SETVAL('role_role_id_seq', COALESCE(MAX(role_id), 1) ) FROM role;

INSERT INTO appuser (appuser_id, appuser_name, appuser_password) VALUES (0, 'alice', 'a123');
INSERT INTO appuser (appuser_id, appuser_name, appuser_password) VALUES (1, 'bob', 'b123');
INSERT INTO appuser (appuser_id, appuser_name, appuser_password) VALUES (2, 'charlie', 'c123');

SELECT SETVAL('appuser_appuser_id_seq', COALESCE(MAX(appuser_id), 1) ) FROM appuser;

INSERT INTO userrole (userrole_appuser_id, userrole_role_id) VALUES (0, 0);
INSERT INTO userrole (userrole_appuser_id, userrole_role_id) VALUES (0, 1);
INSERT INTO userrole (userrole_appuser_id, userrole_role_id) VALUES (1, 0);
INSERT INTO userrole (userrole_appuser_id, userrole_role_id) VALUES (2, 0);

INSERT INTO magazinecategory (magazinecategory_id, magazinecategory_name) VALUES (0, 'GAMES'); 
INSERT INTO magazinecategory (magazinecategory_id, magazinecategory_name) VALUES (1, 'LITERATURE');
INSERT INTO magazinecategory (magazinecategory_id, magazinecategory_name) VALUES (2, 'HISTORICAL');
INSERT INTO magazinecategory (magazinecategory_id, magazinecategory_name) VALUES (3, 'PHILOSOPHY');

INSERT INTO magazine (magazine_reference, magazine_publisher, magazine_title, magazine_magazinecategory_id, magazine_price, magazine_number_of_pages) VALUES ('ISSN1780-0982', 'Project029', 'GameStar', 0, 1890, 115);
INSERT INTO magazine (magazine_reference, magazine_publisher, magazine_title, magazine_magazinecategory_id, magazine_price, magazine_number_of_pages) VALUES ('ISSN8725-9892', 'Irodalom Kft.', 'Life and Literature', 1, 320, 24);
INSERT INTO magazine (magazine_reference, magazine_publisher, magazine_title, magazine_magazinecategory_id, magazine_price, magazine_number_of_pages) VALUES ('ISSN2062-5200', 'Kossuth', 'BBC History', 2, 890, 85);