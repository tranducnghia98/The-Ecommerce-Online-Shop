package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Product;
import edu.poly.shop.reponsitory.ProductReponsitory;
import edu.poly.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductReponsitory productReponsitory;

	@Override
	public <S extends Product> S save(S entity) {
		return productReponsitory.save(entity);
	}


	@Override
	public List<Product> findByNameProductContaining(String nameProduct) {
		return productReponsitory.findByNameProductContaining(nameProduct);
	}


	@Override
	public Page<Product> findByNameProductContaining(String nameProduct, Pageable pageable) {
		return productReponsitory.findByNameProductContaining(nameProduct, pageable);
	}


	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return productReponsitory.findOne(example);
	}

	@Override
	public List<Product> findAll() {
		return productReponsitory.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productReponsitory.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productReponsitory.findAll(sort);
	}

	@Override
	public List<Product> findAllById(Iterable<Long> ids) {
		return productReponsitory.findAllById(ids);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return productReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		productReponsitory.flush();
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return productReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productReponsitory.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Product> entities) {
		productReponsitory.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return productReponsitory.existsById(id);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return productReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Product> entities) {
		productReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return productReponsitory.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productReponsitory.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return productReponsitory.count();
	}

	@Override
	public void deleteAllInBatch() {
		productReponsitory.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		productReponsitory.deleteById(id);
	}

	@Override
	public Product getOne(Long id) {
		return productReponsitory.getOne(id);
	}

	@Override
	public void delete(Product entity) {
		productReponsitory.delete(entity);
	}

	@Override
	public Product getById(Long id) {
		return productReponsitory.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		productReponsitory.deleteAll(entities);
	}

	@Override
	public Product getReferenceById(Long id) {
		return productReponsitory.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		productReponsitory.deleteAll();
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return productReponsitory.findAll(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return productReponsitory.findAll(example, sort);
	}
	
	
}
