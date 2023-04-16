package com.information.dao;

import java.util.List;
import org.springframework.http.ResponseEntity;


import com.object.classes.CompanyDetails;
import com.object.classes.CompanyDetailsResp;

public interface CompanyDetailsDAO {
	
	public ResponseEntity<String> storeImage(String name, byte[] data);
	ResponseEntity<List<CompanyDetailsResp>> getCompanyData(String inputParam);
	
}
