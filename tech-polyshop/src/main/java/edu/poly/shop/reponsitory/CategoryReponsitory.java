package edu.poly.shop.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Category;


@Repository
	public interface CategoryReponsitory extends JpaRepository<Category, Long> {
	
		List<Category> findByNameCategoryContaining(String nameCategory);
		Page<Category> findByNameCategoryContaining(String nameCategory, Pageable pageable);
}
