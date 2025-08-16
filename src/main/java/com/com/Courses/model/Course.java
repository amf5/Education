package com.com.Courses.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import jakarta.persistence.ManyToOne;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Course {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String title;

private String description;

private BigDecimal price;
private String image;

@ManyToOne()
private User professor;
/*@ManyToMany
@JoinTable(
    name = "course_student",
    joinColumns =   @JoinColumn(name = "course_id"),
    inverseJoinColumns  = @JoinColumn(name = "student_id")
)
private Set<User> Students;*/

/*@OneToMany(mappedBy = "course")
private List<Enrollment> enrollments = new ArrayList();*/
@CreatedDate
@Column(updatable = false)
private LocalDateTime createdAt;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public User getProfessor() {
	return professor;
}
public void setProfessor(User professor) {
	this.professor = professor;
}

public LocalDateTime getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}
public Course(String title, String description, BigDecimal price, String image, User professor,
		LocalDateTime createdAt) {
	super();
	this.title = title;
	this.description = description;
	this.price = price;
	this.image = image;
	this.professor = professor;

	this.createdAt = createdAt;
}
public Course() {
	super();
}



}
