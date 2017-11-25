CREATE VIEW markdetail AS
	SELECT
		ROW_NUMBER() OVER() AS markdetail_id,
		mark_subject_id AS markdetail_subject_id,
		student_institute_id AS markdetail_institute_id,
		mark_grade AS markdetail_grade,
		DATE_PART('year', mark_date) AS markdetail_year     
	FROM mark
		INNER JOIN student ON ( mark_student_id = student_id ) 
	WHERE ( 1 = 1 );