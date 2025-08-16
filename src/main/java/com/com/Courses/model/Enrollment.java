package com.com.Courses.model;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Enrollment {
	 @EmbeddedId
	    private EnrollmentId id;

	    @ManyToOne
	    @MapsId("studentId") 
	    @JoinColumn(name = "student_id")
	    private User student;
	    @ManyToOne
	    @MapsId("courseId")
	    @JoinColumn(name = "course_id")
	    private Course course;
	    private LocalDateTime enrolledAt;
	     private float progress	;
	private Boolean    completed;
	public EnrollmentId getId() {
		return id;
	}
	public void setId(EnrollmentId id) {
		this.id = id;
	}
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public LocalDateTime getEnrolledAt() {
		return enrolledAt;
	}
	public void setEnrolledAt(LocalDateTime enrolledAt) {
		this.enrolledAt = enrolledAt;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public Enrollment(User student, Course course, LocalDateTime enrolledAt, float progress, Boolean completed) {
		super();
		this.student = student;
		this.course = course;
		this.enrolledAt = enrolledAt;
		this.progress = progress;
		this.completed = completed;
	}
	public Enrollment() {
		super();
	}
	
	
	
}
