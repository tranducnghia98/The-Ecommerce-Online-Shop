package edu.poly.shop.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Long>{

	List<Product> findByNameProductContaining(String nameProduct);
	Page<Product> findByNameProductContaining(String nameProduct, Pageable pageable);
	
}
