package edu.poly.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

	private Long productId;
	private String nameProduct;
	private double unitPrice;
	private int quantity;
	
}
