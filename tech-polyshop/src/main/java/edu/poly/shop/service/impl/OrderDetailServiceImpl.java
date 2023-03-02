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

import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.reponsitory.OrderDetailReponsitory;
import edu.poly.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl  implements OrderDetailService{

	@Autowired
	OrderDetailReponsitory orderDetailReponsitory;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailReponsitory.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return orderDetailReponsitory.findOne(example);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailReponsitory.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailReponsitory.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailReponsitory.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return orderDetailReponsitory.findAllById(ids);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailReponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		orderDetailReponsitory.flush();
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return orderDetailReponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailReponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailReponsitory.findAll(example, pageable);
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return orderDetailReponsitory.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		orderDetailReponsitory.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return orderDetailReponsitory.existsById(id);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return orderDetailReponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		orderDetailReponsitory.deleteAllInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return orderDetailReponsitory.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderDetailReponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderDetailReponsitory.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return orderDetailReponsitory.count();
	}

	@Override
	public void deleteAllInBatch() {
		orderDetailReponsitory.deleteAllInBatch();
	}

	@Override
	public void deleteById(Integer id) {
		orderDetailReponsitory.deleteById(id);
	}

	@Override
	public OrderDetail getOne(Integer id) {
		return orderDetailReponsitory.getOne(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailReponsitory.delete(entity);
	}

	@Override
	public OrderDetail getById(Integer id) {
		return orderDetailReponsitory.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderDetailReponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		orderDetailReponsitory.deleteAll(entities);
	}

	@Override
	public OrderDetail getReferenceById(Integer id) {
		return orderDetailReponsitory.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		orderDetailReponsitory.deleteAll();
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailReponsitory.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailReponsitory.findAll(example, sort);
	}
	
	
	
}
