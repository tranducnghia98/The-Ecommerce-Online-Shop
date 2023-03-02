package edu.poly.shop.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	
	private Long productId;
	private String nameProduct;
	private int quantity;
	private double unitPrice;
	private String image;
	private MultipartFile imageFile;
	
	private String description;
	private double discount;
	private Date enteredDate;
	private short status;
	private Long categoryId;
	
	//nhaanj biet che do edit or new
	private Boolean isEdit;
}
