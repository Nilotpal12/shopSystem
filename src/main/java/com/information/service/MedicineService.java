package com.information.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.information.dao.MedicineDetailsDAO;
import com.information.entity.Medicine;

import io.netty.channel.socket.DuplexChannel;

@Service
public class MedicineService {
	
	@Autowired
	MedicineDetailsDAO medicineDao;
	
	
    public Medicine saveMedicine(Medicine medicine)
    {
    	List<Medicine> mediList= medicineDao.findByMedicineName(medicine.getName());
    	if(mediList.size()== 0) {
    		return medicineDao.saveAndFlush(medicine);
    	}else {
    		Medicine dupMedicine =  mediList.get(0);
    		int quantity = dupMedicine.getQuantity_in_stock();
    		quantity += medicine.getQuantity_in_stock();
    		dupMedicine.setQuantity_in_stock(quantity);
    		return medicineDao.save(dupMedicine);
    		
    	}
    }
 
    
    public List<Medicine> fetchMedicineList(int compID)
    {
        return (List<Medicine>)medicineDao.findByCompanyId(compID);
    }
    
    public List<Medicine> searchMedicineByName(String medName)
    {
        return (List<Medicine>)medicineDao.findMedicinesByName(medName);
    }

}
