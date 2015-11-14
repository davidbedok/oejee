INSERT INTO event (event_id, event_puller, event_prizepool, event_date) VALUES (1, 'Tom S. Chew', 53464, now()::timestamp);
INSERT INTO event (event_id, event_puller, event_prizepool, event_date) VALUES (2, 'Juanita A. Jenkins', 853321, now()::timestamp);
INSERT INTO event (event_id, event_puller, event_prizepool, event_date) VALUES (3, 'Preston J. Fleming', 21446, now()::timestamp);
INSERT INTO event (event_id, event_puller, event_prizepool, event_date) VALUES (4, 'Linda R. Ray', 942321, now()::timestamp);
 
SELECT SETVAL('event_event_id_seq', COALESCE(MAX(event_id), 1) ) FROM event;

INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (1, 23);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (1, 54);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (1, 12);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (1, 87);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (1, 44);

INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (2, 12);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (2, 78);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (2, 34);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (2, 76);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (2, 12);

INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (3, 6);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (3, 81);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (3, 26);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (3, 36);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (3, 52);

INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (4, 21);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (4, 35);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (4, 49);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (4, 2);
INSERT INTO drawnnumber (drawnnumber_event_id, drawnnumber_value) VALUES (4, 18);