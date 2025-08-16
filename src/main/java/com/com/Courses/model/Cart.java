package com.com.Courses.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cart {
	@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@OneToOne
private User user;
@CreatedDate
@Column(updatable = false)
private LocalDateTime createdAt;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public LocalDateTime getCreatedAt() {
	return createdAt;
}
public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}
public Cart(User user, LocalDateTime createdAt) {
	super();
	this.user = user;
	this.createdAt = createdAt;
}
public Cart() {
	super();
}



}
