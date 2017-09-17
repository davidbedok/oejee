package hu.qwaevisz.school.persistence.entity.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import hu.qwaevisz.school.persistence.entity.Subject;
import hu.qwaevisz.school.persistence.entity.trunk.Institute;
import hu.qwaevisz.school.persistence.parameter.MarkDetailParameter;
import hu.qwaevisz.school.persistence.query.MarkDetailQuery;

@Entity
@Table(name = "markdetail")
@NamedQueries(value = { //
		@NamedQuery(name = MarkDetailQuery.GET_AVG_MARKDETAILS, query = "SELECT new hu.qwaevisz.school.persistence.result.MarkDetailResult(md.institute, md.year, AVG(md.grade)) FROM MarkDetail md WHERE md.subject.id=:"
				+ MarkDetailParameter.SUBJECT_ID + " GROUP BY md.institute, md.year ORDER BY md.institute, md.year"),
		//
})
public class MarkDetail implements Serializable {

	private static final long serialVersionUID = 4836583743719198725L;

	@Id
	@Column(name = "markdetail_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "markdetail_subject_id", referencedColumnName = "subject_id", nullable = false)
	private Subject subject;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "markdetail_institute_id", nullable = false)
	private Institute institute;

	@Column(name = "markdetail_grade", nullable = false)
	private Integer grade;

	@Column(name = "markdetail_year")
	private Date year;

	public MarkDetail() {

	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "MarkDetail [id=" + this.id + ", subject=" + this.subject + ", institute=" + this.institute + ", grade=" + this.grade + ", year=" + this.year
				+ "]";
	}

}
