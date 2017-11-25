SELECT
	markdetail_institute_id,
	markdetail_year,
	AVG(markdetail_grade) 
FROM 
	markdetail
		INNER JOIN subject ON
			( markdetail_subject_id = subject_id )
WHERE ( 1 = 1 )
	AND ( subject_name = 'Sybase PowerBuilder' )
GROUP BY
	markdetail_institute_id,
	markdetail_year 
ORDER BY
	markdetail_institute_id,
	markdetail_year;