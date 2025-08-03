package com.com.Courses.dto;

public class ResponseUpdatedUser {
	private String imageUrl;
	private String name;
	private String youTube;
	private String linkedIn;
	private String gitHub;
	private String faceBook;
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public ResponseUpdatedUser(String imageUrl, String name, String youTube, String linkedIn, String gitHub,
			String faceBook) {
		super();
		this.imageUrl = imageUrl;
		this.name = name;
		this.youTube = youTube;
		this.linkedIn = linkedIn;
		this.gitHub = gitHub;
		this.faceBook = faceBook;
	}
	public ResponseUpdatedUser() {
		super();
	}
	
	
	
	
	
}
