package com.com.Courses.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;



@Entity
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

private String description;

@ManyToMany@JoinTable(
	    name = "category_course",
	    joinColumns =   @JoinColumn(name = "category_id"),
	    inverseJoinColumns  = @JoinColumn(name = "course_id")
	)
private Set<Course> course;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}





public Category(String name, String description, Set<Course> course) {
	super();
	this.name = name;
	this.description = description;
	this.course = course;
}

public Set<Course> getCourse() {
	return course;
}

public void setCourse(Set<Course> course) {
	this.course = course;
}

public Category() {
	super();
}



}
