package com.com.Courses.model;

import java.util.List;

import com.com.Courses.domain.RoleEnum;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String account;
	private String image;
	private String youTube;
	private String linkedIn;
	private String gitHub;
	private String faceBook;
	
public String getImage() {
		return image;
	}
	public String getYouTube() {
	return youTube;
}
public void setYouTube(String youTube) {
	this.youTube = youTube;
}
public String getLinkedIn() {
	return linkedIn;
}
public void setLinkedIn(String linkedIn) {
	this.linkedIn = linkedIn;
}
public String getGitHub() {
	return gitHub;
}
public void setGitHub(String gitHub) {
	this.gitHub = gitHub;
}
public String getFaceBook() {
	return faceBook;
}
public void setFaceBook(String faceBook) {
	this.faceBook = faceBook;
}
	public void setImage(String image) {
		this.image = image;
	}
	@Column(length = 500)
	private String token;
	
	private boolean acctivate;
	@ElementCollection(fetch = FetchType.EAGER, targetClass = RoleEnum.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	private List<RoleEnum> roles;

	
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<RoleEnum> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEnum> roles) {
		this.roles = roles;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean isAcctivate() {
		return acctivate;
	}
	public void setAcctivate(boolean acctivate) {
		this.acctivate = acctivate;
	}
	
	public User() {
		super();
	}
	public List<RoleEnum> getRole() {
		return roles;
	}
	public void setRole(List<RoleEnum> roles) {
		this.roles = roles;
	}
	public User(Long id, String name, String email, String password, String account, boolean acctivate,
			List<RoleEnum> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.account = account;
		this.acctivate = acctivate;
		this.roles = roles;
	}
	public User(String name, String email, String password, String account, boolean acctivate, List<RoleEnum> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.account = account;
		this.acctivate = acctivate;
		this.roles = roles;
	}
	
	
	
	
	
	
	
	
	

}
