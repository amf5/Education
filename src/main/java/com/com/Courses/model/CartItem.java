package com.com.Courses.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Cart cart;
	
	@ManyToOne
	private  Course course;

	private Integer integer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public CartItem(Cart cart, Course course, Integer integer) {
		super();
		this.cart = cart;
		this.course = course;
		this.integer = integer;
	}

	public CartItem() {
		super();
	}
	
	
}
