package hu.qwaevisz.school.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hu.qwaevisz.school.persistence.parameter.SubjectParameter;

@Entity
@Table(name = "subject")
@NamedQueries(value = { //
		@NamedQuery(name = Subject.GET_BY_ID, query = "SELECT s FROM Subject s WHERE s.id=:" + SubjectParameter.ID),
		@NamedQuery(name = Subject.GET_BY_NAME, query = "SELECT s FROM Subject s WHERE s.name=:" + SubjectParameter.NAME)
		//
})
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_BY_ID = "Subject.getById";
	public static final String GET_BY_NAME = "Subject.getByName";

	@Id
	@SequenceGenerator(name = "generatorSubject", sequenceName = "subject_subject_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorSubject")
	@Column(name = "subject_id", nullable = false, updatable = false, insertable = false)
	private Long id;

	@Column(name = "subject_name", nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "subject_teacher_id", referencedColumnName = "teacher_id", nullable = false)
	private Teacher teacher;

	@Column(name = "subject_description", nullable = false)
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subject")
	private final Set<Mark> marks;

	public Subject() {
		this.marks = new HashSet<>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Mark> getMarks() {
		return this.marks;
	}

	@Override
	public String toString() {
		return "Subject [id=" + this.id + ", name=" + this.name + ", teacher=" + this.teacher + ", description=" + this.description + "]";
	}

}
