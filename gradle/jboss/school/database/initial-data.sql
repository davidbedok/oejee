INSERT INTO institute (institute_id, institute_name) VALUES (0, 'BANKI'); 
INSERT INTO institute (institute_id, institute_name) VALUES (1, 'KANDO'); 
INSERT INTO institute (institute_id, institute_name) VALUES (2, 'NEUMANN'); 

INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (1, 'Tom S. Chew', 'PA97205', 0);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (2, 'Juanita A. Jenkins', 'WI53085', 0);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (3, 'Preston J. Fleming', 'KS66762', 0);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (4, 'Loretta T. Holder', 'CA90045', 1);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (5, 'William M. Gonzalez', 'TX78476', 1);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (6, 'Evelyn R. Ragland', 'VA22304', 1);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (7, 'Linda R. Ray', 'NV89101', 1);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (8, 'Dan N. Pearman', 'ND58801', 1);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (9, 'Roy A. Jennings', 'IL60606', 2);
INSERT INTO student (student_id, student_name, student_neptun, student_institute_id) VALUES (10, 'Margaret O. Fernandez', 'MA02148', 2);

SELECT SETVAL('student_student_id_seq', COALESCE(MAX(student_id), 1) ) FROM student;

INSERT INTO teacher (teacher_id, teacher_name, teacher_neptun) VALUES (1, 'Lorine B. Pine', 'MD21921');
INSERT INTO teacher (teacher_id, teacher_name, teacher_neptun) VALUES (2, 'Richard B. Cambra', 'UT84113');
INSERT INTO teacher (teacher_id, teacher_name, teacher_neptun) VALUES (3, 'Christine W. Culp', 'OK73109');

SELECT SETVAL('teacher_teacher_id_seq', COALESCE(MAX(teacher_id), 1) ) FROM teacher;

INSERT INTO subject (subject_id, subject_name, subject_teacher_id, subject_description) VALUES (1, 'Java Enterprise Edition', 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.');
INSERT INTO subject (subject_id, subject_name, subject_teacher_id, subject_description) VALUES (2, 'Sybase PowerBuilder', 2, 'Donec rhoncus lacus quis est cursus aliquet.');
INSERT INTO subject (subject_id, subject_name, subject_teacher_id, subject_description) VALUES (3, 'Python Programming', 3, 'Fusce sem turpis, iaculis non lobortis ac, accumsan et purus.');
INSERT INTO subject (subject_id, subject_name, subject_teacher_id, subject_description) VALUES (4, 'C/C++ Programming', 1, 'Maecenas a nisl nisl. Nam consectetur auctor arcu, sed elementum justo suscipit nec.');
INSERT INTO subject (subject_id, subject_name, subject_teacher_id, subject_description) VALUES (5, 'Scala Functional Programming', 2, 'Cras posuere cursus nulla vel feugiat. Integer sagittis efficitur enim vitae posuere.');

SELECT SETVAL('subject_subject_id_seq', COALESCE(MAX(subject_id), 1) ) FROM subject;

INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (1, 1, 5, 'Morbi', now()::timestamp);
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (2, 1, 4, 'Praesent', TO_TIMESTAMP(1422956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (3, 2, 3, 'Phasellus', TO_TIMESTAMP(1411956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (4, 2, 2, 'Vivamus', TO_TIMESTAMP(1401956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (5, 2, 1, 'Facilisis', TO_TIMESTAMP(1391956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (1, 3, 5, 'Malesuada', TO_TIMESTAMP(1382956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (2, 4, 4, 'Nunc', TO_TIMESTAMP(1372956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (3, 4, 3, 'Laoreet', TO_TIMESTAMP(1362956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (4, 6, 2, 'Sociis', TO_TIMESTAMP(1352956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (5, 6, 1, 'Vehicula', TO_TIMESTAMP(1342956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (1, 6, 5, 'Vestibulum', TO_TIMESTAMP(1348956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (2, 7, 4, 'Neque', TO_TIMESTAMP(1356956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (3, 8, 3, 'Aliquet', TO_TIMESTAMP(1355956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (3, 8, 2, 'Felis', TO_TIMESTAMP(1369956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (4, 8, 1, 'Consequat', TO_TIMESTAMP(1371956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (5, 9, 5, 'Elementum', TO_TIMESTAMP(1379956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (2, 9, 4, 'Tempus', TO_TIMESTAMP(1388956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (2, 9, 3, 'Ante', TO_TIMESTAMP(1394956934));
INSERT INTO mark (mark_subject_id, mark_student_id, mark_grade, mark_note, mark_date) VALUES (1, 9, 2, 'Quam', TO_TIMESTAMP(1406956934));

SELECT SETVAL('mark_mark_id_seq', COALESCE(MAX(mark_id), 1) ) FROM mark;