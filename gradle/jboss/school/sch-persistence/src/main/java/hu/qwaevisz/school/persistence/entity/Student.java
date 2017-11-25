package hu.qwaevisz.school.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hu.qwaevisz.school.persistence.entity.trunk.Institute;
import hu.qwaevisz.school.persistence.parameter.StudentParameter;

@Entity
@Table(name = "student")
@NamedQueries(value = { //
		@NamedQuery(name = Student.COUNT_BY_NAME, query = "SELECT COUNT(s) FROM Student s WHERE s.neptun=:" + StudentParameter.NEPTUN),
		@NamedQuery(name = Student.GET_BY_NEPTUN, query = "SELECT st FROM Student st LEFT JOIN FETCH st.marks m LEFT JOIN FETCH m.subject su LEFT JOIN FETCH su.teacher WHERE st.neptun=:"
				+ StudentParameter.NEPTUN),
		@NamedQuery(name = Student.GET_BY_ID, query = "SELECT s FROM Student s WHERE s.id=:" + StudentParameter.ID),
		@NamedQuery(name = Student.GET_ALL, query = "SELECT s FROM Student s ORDER BY s.name"),
		@NamedQuery(name = Student.REMOVE_BY_NEPTUN, query = "DELETE FROM Student s WHERE s.neptun=:" + StudentParameter.NEPTUN),
		@NamedQuery(name = Student.GET_BY_IDS, query = "SELECT st FROM Student st LEFT JOIN FETCH st.marks m LEFT JOIN FETCH m.subject su LEFT JOIN FETCH su.teacher WHERE st.id IN :ids")
		//
})
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String COUNT_BY_NAME = "Student.countByName";
	public static final String GET_BY_ID = "Student.getById";
	public static final String GET_BY_IDS = "Student.getByIds";
	public static final String GET_BY_NEPTUN = "Student.getByNeptun";
	public static final String GET_ALL = "Student.getAll";
	public static final String REMOVE_BY_NEPTUN = "Student.removeByNeptun";

	@Id
	@SequenceGenerator(name = "generatorStudent", sequenceName = "student_student_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorStudent")
	@Column(name = "student_id", nullable = false, updatable = false, insertable = false)
	private Long id;

	@Column(name = "student_name", nullable = false)
	private String name;

	@Column(name = "student_neptun", nullable = false)
	private String neptun;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "student_institute_id", nullable = false)
	private Institute institute;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	private final Set<Mark> marks;

	public Student() {
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

	public String getNeptun() {
		return this.neptun;
	}

	public void setNeptun(String neptun) {
		this.neptun = neptun;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Set<Mark> getMarks() {
		return this.marks;
	}

	@Override
	public String toString() {
		return "Student [id=" + this.id + ", name=" + this.name + ", neptun=" + this.neptun + ", institute=" + this.institute + "]";
	}

}
