package edu.poly.shop.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {
	//cho phép lưu hay chuyển qua mạng toàn bộ đối tượng 
	//là thể hiện của lớp đc khai báo implement Serialiable
	
	private Long  categoryId;
	
	@NotEmpty
	@Length(min = 5)
	private String nameCategory;
	
	private Boolean isEdit = false;

	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", nameCategory=" + nameCategory + ", isEdit=" + isEdit + "]";
	}


}
