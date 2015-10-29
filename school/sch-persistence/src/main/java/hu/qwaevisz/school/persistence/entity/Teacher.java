package hu.qwaevisz.school.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

	private static final long serialVersionUID = -1744926308793163373L;

	@Id
	@SequenceGenerator(name = "generatorTeacher", sequenceName = "teacher_teacher_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorTeacher")
	@Column(name = "teacher_id", nullable = false, updatable = false, insertable = false)
	private Long id;

	@Column(name = "teacher_name", nullable = false)
	private String name;

	@Column(name = "teacher_neptun", nullable = false)
	private String neptun;

	public Teacher() {

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

	@Override
	public String toString() {
		return "Teacher [id=" + this.id + ", name=" + this.name + ", neptun=" + this.neptun + "]";
	}

}
