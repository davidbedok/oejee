SELECT
	markdetail.student_institute_id,
	markdetail.mark_year,
	AVG(markdetail.mark_grade)
FROM
	(
		SELECT
			mark_subject_id,
			student_institute_id,
			mark_grade,
			DATE_TRUNC('year', mark_date) AS mark_year     
		FROM mark
			INNER JOIN student ON ( mark_student_id = student_id ) 
		WHERE ( 1 = 1 ) 
	) AS markdetail
WHERE ( 1 = 1 )
	AND ( markdetail.mark_subject_id = 2 )
GROUP BY
	markdetail.student_institute_id,
	markdetail.mark_year
ORDER BY
	markdetail.student_institute_id,
	markdetail.mark_year