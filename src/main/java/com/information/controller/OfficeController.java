package com.information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.information.service.InformationService;
import com.shop.constants.ShopConstants.BasicConstants;

@RestController
@RequestMapping("/office")
public class OfficeController {
	
	@Autowired
	InformationService informationService;
	
	@PostMapping("/addCompanyDetails")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name) {
		ResponseEntity<String> response;
		try {
			response = informationService.saveCompany(file, name);

		} catch (Exception e) {
			return ResponseEntity.ok().body(BasicConstants.SUCCESS.toString());
		}
		return response;
	}
}
