package com.com.Courses.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class PaymentItem {

@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long id;
@ManyToOne
private Payment payment;
@ManyToOne
private Course course;

private BigDecimal price;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Payment getPayment() {
	return payment;
}

public void setPayment(Payment payment) {
	this.payment = payment;
}

public Course getCourse() {
	return course;
}

public void setCourse(Course course) {
	this.course = course;
}

public BigDecimal getPrice() {
	return price;
}

public void setPrice(BigDecimal price) {
	this.price = price;
}

public PaymentItem() {
	super();
}

public PaymentItem(Payment payment, Course course, BigDecimal price) {
	super();
	this.payment = payment;
	this.course = course;
	this.price = price;
}


}
