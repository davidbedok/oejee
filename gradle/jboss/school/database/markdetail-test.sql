SELECT
	markdetail_institute_id,
	markdetail_year,
	AVG(markdetail_grade) 
FROM 
	markdetail
WHERE ( 1 = 1 )
	AND ( markdetail_subject_id = 2 )
GROUP BY
	markdetail_institute_id,
	markdetail_year 
ORDER BY
	markdetail_institute_id,
	markdetail_year;