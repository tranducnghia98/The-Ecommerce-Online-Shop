package edu.poly.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto implements Serializable{
	
	
	
	private Integer customerId;
	

	private String nameCustomer;
	
	
	private String email;
	

	private String password;
	
	
	private String phone;
	
	
	private Date registeredDate =  new Date();
	
	private boolean role= true;

	private short status;
	
	
	private Boolean isEdit = false;
}

























