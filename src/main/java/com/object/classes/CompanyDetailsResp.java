package com.object.classes;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CompanyDetailsResp implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Blob image;
	private int Id;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public Blob getImage() {
		return image;
	}
    @JsonProperty("photoData")
    public String getPhotoBase64() {
      // just assuming it is a jpeg. you would need to cater for different media types
      try {
		return "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(image.getBytes(1, (int) image.length())));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
    }
	public void setImage(Blob image) {
		this.image = image;
	}
	
	public int getId() {
		return Id;
	}
	
	public void setId(int id) {
		Id = id;
	}
}
