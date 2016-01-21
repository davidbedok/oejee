CREATE TABLE institute (
	institute_id INTEGER NOT NULL,
	institute_name CHARACTER VARYING(100) NOT NULL,
	CONSTRAINT PK_INSTITUTE_ID PRIMARY KEY (institute_id)
);

ALTER TABLE institute OWNER TO postgres;

CREATE TABLE student (
	student_id SERIAL NOT NULL,
	student_name CHARACTER VARYING(100) NOT NULL,
	student_neptun CHARACTER VARYING(10) NOT NULL,
	student_institute_id INTEGER NOT NULL,
	CONSTRAINT PK_STUDENT_ID PRIMARY KEY (student_id),
	CONSTRAINT FK_STUDENT_INSTITUTE_ID FOREIGN KEY (student_institute_id)
	  REFERENCES institute (institute_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE student OWNER TO postgres;

CREATE UNIQUE INDEX UI_STUDENT_NEPTUN ON student USING btree (student_neptun);

CREATE TABLE teacher (
	teacher_id SERIAL NOT NULL,
	teacher_name CHARACTER VARYING(100) NOT NULL,
	teacher_neptun CHARACTER VARYING(10) NOT NULL,
	CONSTRAINT PK_TEACHER_ID PRIMARY KEY (teacher_id)
);

ALTER TABLE teacher OWNER TO postgres;

CREATE UNIQUE INDEX UI_TEACHER_NEPTUN ON teacher USING btree (teacher_neptun);

CREATE TABLE subject (
	subject_id SERIAL NOT NULL,
	subject_name CHARACTER VARYING(100) NOT NULL,
	subject_teacher_id INTEGER NOT NULL,
	subject_description CHARACTER VARYING(500) NOT NULL,
	CONSTRAINT PK_SUBJECT_ID PRIMARY KEY (subject_id),
	CONSTRAINT FK_SUBJECT_TEACHER FOREIGN KEY (subject_teacher_id)
	  REFERENCES teacher (teacher_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT
);

ALTER TABLE subject OWNER TO postgres;

CREATE UNIQUE INDEX UI_SUBJECT_NAME ON subject USING btree (subject_name);

CREATE TABLE mark (
	mark_id SERIAL NOT NULL,
	mark_student_id INTEGER NOT NULL,
	mark_subject_id INTEGER NOT NULL,
	mark_grade INTEGER NOT NULL,
	mark_note CHARACTER VARYING(50) NOT NULL,
	mark_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	CONSTRAINT PK_MARK_ID PRIMARY KEY (mark_id),
	CONSTRAINT FK_MARK_STUDENT FOREIGN KEY (mark_student_id)
	  REFERENCES student (student_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT FK_MARK_SUBJECT FOREIGN KEY (mark_subject_id)
	  REFERENCES subject (subject_id) MATCH SIMPLE ON UPDATE RESTRICT ON DELETE RESTRICT	  
);

ALTER TABLE mark OWNER TO postgres;

CREATE VIEW markdetail AS
	SELECT
		ROW_NUMBER() OVER() AS markdetail_id,
		mark_subject_id AS markdetail_subject_id,
		student_institute_id AS markdetail_institute_id,
		mark_grade AS markdetail_grade,
		DATE_TRUNC('year', mark_date) AS markdetail_year     
	FROM mark
		INNER JOIN student ON ( mark_student_id = student_id ) 
	WHERE ( 1 = 1 );
	
ALTER TABLE markdetail OWNER TO postgres;
