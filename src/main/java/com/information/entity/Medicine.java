package com.information.entity;

import org.hibernate.annotations.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "MEDICINES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medicine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "MEDICINE_ID")
	private int med_id;
	
	@Column(name = "COMPANY_ID")
	private int comp_id;
	
	@Column(name = "MEDICINE_NAME")
	private String name;
	
	@Column(name = "PRICE_RATE")
	private Float price_rate;
	
	@Column(name = "QUANTITY_IN_STOCK")
	private int quantity_in_stock;
	
	public int getMed_id() {
		return med_id;
	}
	public void setMed_id(int med_id) {
		this.med_id = med_id;
	}
	public int getComp_id() {
		return comp_id;
	}
	public void setComp_id(int comp_id) {
		this.comp_id = comp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice_rate() {
		return price_rate;
	}
	public void setPrice_rate(Float price_rate) {
		this.price_rate = price_rate;
	}
	public int getQuantity_in_stock() {
		return quantity_in_stock;
	}
	public void setQuantity_in_stock(int quantity_in_stock) {
		this.quantity_in_stock = quantity_in_stock;
	}

}
