package com.information.dao;

import org.springframework.http.ResponseEntity;

public interface CompanyDetailsDAO {
	
	public ResponseEntity<String> storeImage(String name, byte[] data);
	

}
