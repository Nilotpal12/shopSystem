package com.information.dao;

import java.util.List;
import org.springframework.http.ResponseEntity;


import com.object.classes.CompanyDetails;

public interface CompanyDetailsDAO {
	
	public ResponseEntity<String> storeImage(String name, byte[] data);
	ResponseEntity<List<CompanyDetails>> getCompanyData(String inputParam);
	
}
