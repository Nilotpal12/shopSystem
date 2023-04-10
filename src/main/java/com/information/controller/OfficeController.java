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
	public ResponseEntity<String> AddMedicine(@RequestBody Medicine medicine) {
		ResponseEntity<String> response;
		try {
			System.out.println(medicine.getName());
			System.out.println(medicine.getPrice_rate());
			Medicine retmedicine = medicineService.saveMedicine(medicine);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Medicine SuccessFully Added");
	}

	@GetMapping("/medicines")
	public List<Medicine> fetchDepartmentList() {
		return medicineService.fetchMedicineList();
    }
	
	@GetMapping("/search")
	public ResponseEntity<List<CompanyDetails>> myControllerMethod(@RequestParam String name) {
	  
		ResponseEntity<List<CompanyDetails>> response;
		try {
		response = informationService.fetchCompanyDetails(name);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
	  return response;
	}
}
