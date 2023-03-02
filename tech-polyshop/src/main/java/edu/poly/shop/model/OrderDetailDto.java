package edu.poly.shop.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDetailDto implements Serializable{

	@NotEmpty
	private Integer orderDetailId;
	
//	@Column(nullable = false)
//	private int orderId;
	
	
	
	@NotEmpty
	private int quantity;
	
	@NotEmpty
	private double unitPrice;
	
	
	private Long  productId;
	
	
	private int orderId;
	
}
