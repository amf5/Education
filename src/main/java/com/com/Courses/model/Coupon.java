package com.com.Courses.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Coupon {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String code;
	
	private Integer discountPrecentage;
	
	private LocalDateTime validFrom;
	
    private LocalDateTime validTo;
    
    private Integer usageLimit;
    
    private boolean isValid;
    @ManyToOne
    private Course course;
    @ManyToOne
    private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getDiscountPrecentage() {
		return discountPrecentage;
	}
	public void setDiscountPrecentage(Integer discountPrecentage) {
		this.discountPrecentage = discountPrecentage;
	}
	public LocalDateTime getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(LocalDateTime validFrom) {
		this.validFrom = validFrom;
	}
	public LocalDateTime getValidTo() {
		return validTo;
	}
	public void setValidTo(LocalDateTime validTo) {
		this.validTo = validTo;
	}
	public Integer getUsageLimit() {
		return usageLimit;
	}
	public void setUsageLimit(Integer usageLimit) {
		this.usageLimit = usageLimit;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Coupon(String code, Integer discountPrecentage, LocalDateTime validFrom, LocalDateTime validTo,
			Integer usageLimit, boolean isValid, Course course, User user) {
		super();
		this.code = code;
		this.discountPrecentage = discountPrecentage;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.usageLimit = usageLimit;
		this.isValid = isValid;
		this.course = course;
		this.user = user;
	}
	public Coupon() {
		super();
	}
    
	
	
	
}
