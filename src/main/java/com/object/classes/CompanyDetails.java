package com.object.classes;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CompanyDetails {

	private String name;
	private MultipartFile image;
}
