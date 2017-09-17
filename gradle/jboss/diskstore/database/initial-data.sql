INSERT INTO diskcategory (diskcategory_id, diskcategory_name) VALUES (0, 'CLASSIC'); 
INSERT INTO diskcategory (diskcategory_id, diskcategory_name) VALUES (1, 'ROCK');
INSERT INTO diskcategory (diskcategory_id, diskcategory_name) VALUES (2, 'POP');
INSERT INTO diskcategory (diskcategory_id, diskcategory_name) VALUES (3, 'OTHER');

INSERT INTO disk (disk_reference, disk_author, disk_title, disk_diskcategory_id, disk_price, disk_number_of_songs) VALUES ('JSB842', 'Johann Sebastian Bach', 'F major Toccata and Fugue', 0, 1400, 3);
INSERT INTO disk (disk_reference, disk_author, disk_title, disk_diskcategory_id, disk_price, disk_number_of_songs) VALUES ('WAM124', 'Wolfgang Amadeus Mozart', 'Requiem Mass in D minor', 0, 1850, 5);
INSERT INTO disk (disk_reference, disk_author, disk_title, disk_diskcategory_id, disk_price, disk_number_of_songs) VALUES ('EAP009', 'Elvis Aaron Presley', 'Something for Everybody', 1, 2250, 5);
INSERT INTO disk (disk_reference, disk_author, disk_title, disk_diskcategory_id, disk_price, disk_number_of_songs) VALUES ('EMOGBU', 'Ennio Morricone', 'The Good, the Bad and the Ugly', 3, 6500, 11);