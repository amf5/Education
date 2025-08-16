package com.com.Courses.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
@Embeddable
public class EnrollmentId implements Serializable{
	private Long studentId;
    private Long courseId;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public EnrollmentId(Long studentId, Long courseId) {
		super();
		this.studentId = studentId;
		this.courseId = courseId;
	}
	public EnrollmentId() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(courseId, studentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrollmentId other = (EnrollmentId) obj;
		return Objects.equals(courseId, other.courseId) && Objects.equals(studentId, other.studentId);
	}
    
}
