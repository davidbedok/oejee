package hu.qwaevisz.school.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import hu.qwaevisz.school.persistence.parameter.MarkParameter;
import hu.qwaevisz.school.persistence.query.MarkQuery;

@Entity
@Table(name = "mark")
@NamedQueries(value = { //
		@NamedQuery(name = MarkQuery.COUNT_BY_STUDENT_NEPTUN, query = "SELECT COUNT(m) FROM Mark m WHERE m.student.neptun=:" + MarkParameter.STUDENT_NEPTUN),
		@NamedQuery(name = MarkQuery.READ_BY_FILTER, query = "SELECT m FROM Mark m WHERE m.student.neptun=:" + MarkParameter.STUDENT_NEPTUN
				+ " AND m.grade BETWEEN :" + MarkParameter.MIN_GRADE + " AND :" + MarkParameter.MAX_GRADE + " AND m.subject.name LIKE :"
				+ MarkParameter.SUBJECT_NAME_TERM)
		//
})
public class Mark implements Serializable {

	private static final long serialVersionUID = 6159250760580178806L;

	@Id
	@SequenceGenerator(name = "generatorMark", sequenceName = "mark_mark_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorMark")
	@Column(name = "mark_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "mark_student_id", referencedColumnName = "student_id", nullable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "mark_subject_id", referencedColumnName = "subject_id", nullable = false)
	private Subject subject;

	@Column(name = "mark_grade", nullable = false)
	private Integer grade;

	@Column(name = "mark_note", nullable = false)
	private String note;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mark_date", nullable = false)
	private Date date;

	public Mark() {
		this(null, null, null, null);
	}

	public Mark(Student student, Subject subject, Integer grade, String note) {
		this.student = student;
		this.subject = subject;
		this.grade = grade;
		this.note = note;
		this.date = new Date();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Mark [id=" + this.id + ", student=" + this.student + ", subject=" + this.subject + ", grade=" + this.grade + ", note=" + this.note + ", date="
				+ this.date + "]";
	}

}
