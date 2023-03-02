package edu.poly.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDto implements Serializable{

	@NotEmpty
	private Integer orderId;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@Column(nullable = false)
	private int customerId;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private short status;
	

}






















