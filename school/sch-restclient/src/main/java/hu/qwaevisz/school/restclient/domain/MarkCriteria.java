package hu.qwaevisz.school.restclient.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "markcriteria")
public class MarkCriteria implements Serializable {

	private static final long serialVersionUID = -5781732904290824240L;

	private String subjectNameTerm;
	private int minimumGrade;
	private int maximumGrade;

	public MarkCriteria() {
		this(null, 0, 0);
	}

	public MarkCriteria(String subjectNameTerm, int minimumGrade, int maximumGrade) {
		this.subjectNameTerm = subjectNameTerm;
		this.minimumGrade = minimumGrade;
		this.maximumGrade = maximumGrade;
	}

	@XmlElement(name = "subject")
	public String getSubjectNameTerm() {
		return this.subjectNameTerm;
	}

	public void setSubjectNameTerm(String subjectNameTerm) {
		this.subjectNameTerm = subjectNameTerm;
	}

	@XmlElement(name = "minimumgrade")
	public int getMinimumGrade() {
		return this.minimumGrade;
	}

	public void setMinimumGrade(int minimumGrade) {
		this.minimumGrade = minimumGrade;
	}

	@XmlElement(name = "maximumgrade")
	public int getMaximumGrade() {
		return this.maximumGrade;
	}

	public void setMaximumGrade(int maximumGrade) {
		this.maximumGrade = maximumGrade;
	}

	@Override
	public String toString() {
		return "MarkCriteria [subjectNameTerm=" + this.subjectNameTerm + ", minimumGrade=" + this.minimumGrade + ", maximumGrade=" + this.maximumGrade + "]";
	}

}
