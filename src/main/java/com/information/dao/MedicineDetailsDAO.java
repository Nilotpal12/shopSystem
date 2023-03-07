package com.information.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.information.entity.Medicine;


public interface MedicineDetailsDAO extends JpaRepository<Medicine, Long>{
	
}
