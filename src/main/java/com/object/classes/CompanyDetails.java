package com.object.classes;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CompanyDetails {

	private String name;
	private MultipartFile image;
	private Long Id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
}
