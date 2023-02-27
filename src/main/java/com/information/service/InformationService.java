package com.information.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.information.dao.CompanyDetailsDAO;

@Service
public class InformationService {

	
	CompanyDetailsDAO companyDetailsDAO;

	public ResponseEntity<String> saveCompany(MultipartFile file, String name) {
		
		ResponseEntity<String> validate = validateInputs(file,name);
		ResponseEntity<String> daoResponse = null;
		
		if(validate.getStatusCode()!=HttpStatus.ACCEPTED) {
			return validate;
		}
		
		try {
			
			byte[] imageData = file.getBytes();
			daoResponse = companyDetailsDAO.storeImage(name, imageData);
		} catch (IOException e) {
			
		}
		
		return daoResponse;
	}

	private ResponseEntity<String> validateInputs(MultipartFile image, String name) {
	    if (image.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image is missing");
	    }
	    if (name.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is missing");
	    }
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	}


	
	

}
