package com.information.daoImpl;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.information.dao.CompanyDetailsDAO;

@Repository
public class CompanyDetailsDAOImpl implements CompanyDetailsDAO{
	
	@Autowired
	private DataSource dataSource;

	@Override
	public ResponseEntity<String> storeImage(String name, byte[] data) {
		Connection conn = null;
		CallableStatement stmt = null;
		int success = 0;
		int id = 0;
		 try {
			  	conn = dataSource.getConnection();
			  	stmt = conn.prepareCall("{call store_image(?, ?, ?, ?, ?)}");
	            
	            Blob imageBlob = conn.createBlob();
	            imageBlob.setBytes(1, data);
	            stmt.setString(1, name);
	            stmt.setBlob(2, imageBlob);
	           
	            
	            stmt.registerOutParameter(3, Types.NUMERIC);  
	            stmt.registerOutParameter(4, Types.NUMERIC);  
	            stmt.registerOutParameter(5, Types.VARCHAR);  
	            
	            stmt.execute();
	            
	            success = stmt.getInt(4);
	            String errorMessage = stmt.getString(5);
	            
	            if (success == 1) {
	                id = stmt.getInt(3);
	                return ResponseEntity.status(HttpStatus.CREATED).body("Image has been stored successfully with ID: " + id);
	            } else {
	                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
	            }
	        }catch (Exception ex) {
	            ex.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while storing the image");
	        }finally {
	        	try {
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	        }
	    }
	}