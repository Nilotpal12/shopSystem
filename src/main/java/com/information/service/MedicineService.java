package com.information.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.information.dao.MedicineDetailsDAO;
import com.information.entity.Medicine;

@Service
public class MedicineService {
	
	@Autowired
	MedicineDetailsDAO medicineDao;
	
    public Medicine saveMedicine(Medicine medicine)
    {
        return medicineDao.saveAndFlush(medicine);
    }
 
    
    public List<Medicine> fetchMedicineList()
    {
        return (List<Medicine>)
        		medicineDao.findAll();
    }

}
