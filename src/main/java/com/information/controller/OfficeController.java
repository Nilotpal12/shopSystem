package com.information.controller;

import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.information.entity.Medicine;
import com.information.service.InformationService;
import com.information.service.MedicineService;
import com.object.classes.CompanyDetails;
import com.object.classes.CompanyDetailsResp;

@RestController
@RequestMapping("/office")
public class OfficeController {
	
	@Autowired
	InformationService informationService;
	
	@Autowired
	MedicineService medicineService;
	
	@PostMapping("/addCompanyDetails")
	public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file,
			@RequestParam("name") String name) {
		ResponseEntity<String> response;
		try {
			response = informationService.saveCompany(file, name);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return response;
	}
	
	@PostMapping("/addMedicineDetails")
	public ResponseEntity<String> AddMedicine(@RequestBody Medicine medicine, @RequestParam String brand) {
		ResponseEntity<List<CompanyDetailsResp>> response;
		try {
			if(brand != null && !brand.equals("")) {
				response = informationService.fetchCompanyDetails(brand);
				List<CompanyDetailsResp> companyDetails = response.getBody();
				if(companyDetails.size()>0) {
					int id = companyDetails.get(0).getId();
					medicine.setComp_id(id);
				}
				Medicine retmedicine = medicineService.saveMedicine(medicine);
				if (retmedicine == null) {
					return ResponseEntity.badRequest().body("Duplicate Medicine");
				}else {
					return ResponseEntity.ok().body("Medicine SuccessFully Added");
				}
			}else {
				return ResponseEntity.badRequest().body("Please provide Company Name");
			}

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		//return ResponseEntity.status(HttpStatus.OK).body("Medicine SuccessFully Added");
	}

	@GetMapping("/medicines")
	public ResponseEntity<List<Medicine>> fetchDepartmentList(@RequestParam String brand) {
		ResponseEntity<List<CompanyDetailsResp>> response;
		try {
			if(brand != null && !brand.equals("")) {
				response= informationService.fetchCompanyDetails(brand);
				List<CompanyDetailsResp> companyDetails = response.getBody();
				if(companyDetails.size()>0) {
					int id = companyDetails.get(0).getId();
					List<Medicine> medList = medicineService.fetchMedicineList(id);
					return ResponseEntity.ok().body(medList);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.badRequest().body(null);
		
    }
	
	@GetMapping("/search")
	public ResponseEntity<List<CompanyDetailsResp>> myControllerMethod(@RequestParam String name) {
	  
		ResponseEntity<List<CompanyDetailsResp>> response;
		try {
		response = informationService.fetchCompanyDetails(name);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	  return response;
	}
	
	@GetMapping("/searchMed")
	public ResponseEntity<List<Medicine>> searchMedicineByName(@RequestParam String name) {
	  
		List<Medicine> response;
		try {
		response = medicineService.searchMedicineByName(name);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	  return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
