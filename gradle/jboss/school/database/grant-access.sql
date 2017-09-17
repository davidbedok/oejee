GRANT SELECT, INSERT, UPDATE, DELETE ON institute, student, teacher, subject, mark TO school_role;
    
GRANT USAGE, SELECT, UPDATE ON student_student_id_seq, teacher_teacher_id_seq, subject_subject_id_seq, mark_mark_id_seq TO school_role;

GRANT SELECT ON markdetail TO school_role;
    