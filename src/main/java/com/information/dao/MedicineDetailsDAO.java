package com.information.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.information.entity.Medicine;


public interface MedicineDetailsDAO extends JpaRepository<Medicine, Long>{
	
	@Query("select u from MEDICINES u where u.name like %?1")
	List<Medicine> findByMedicineName(String medicineName);
	
	@Query("select u from MEDICINES u where u.comp_id = ?1")
	List<Medicine> findByCompanyId(int companyId);


}
