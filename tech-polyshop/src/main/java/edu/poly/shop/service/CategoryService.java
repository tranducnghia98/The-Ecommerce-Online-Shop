package edu.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.shop.domain.Category;

public interface CategoryService {

	void deleteAll();

	void deleteAll(Iterable<? extends Category> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Category getById(Long id);

	void delete(Category entity);

	void deleteById(Long id);

	void deleteAllInBatch();

	long count();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Category> entities);

	boolean existsById(Long id);

	void deleteInBatch(Iterable<Category> entities);

	Optional<Category> findById(Long id);

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	void flush();

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	List<Category> findAllById(Iterable<Long> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> findByNameCategoryContaining(String nameCategory);

	Page<Category> findByNameCategoryContaining(String nameCategory, Pageable pageable);





	

}
