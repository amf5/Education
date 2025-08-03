package com.com.Courses.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

public class RequestUpdateUser {
    @Nonnull
    @NotBlank
	private String name;
	private String youTube;
	private String linkedIn;
	private String gitHub;
	private String faceBook;
	public RequestUpdateUser( String name, String youTube, String linkedIn, String gitHub, String faceBook) {
		super();
		
		this.name = name;
		this.youTube = youTube;
		this.linkedIn = linkedIn;
		this.gitHub = gitHub;
		this.faceBook = faceBook;
	}
	public RequestUpdateUser() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	



}
