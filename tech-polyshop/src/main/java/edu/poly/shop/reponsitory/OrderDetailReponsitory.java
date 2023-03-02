package edu.poly.shop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.OrderDetail;


@Repository
public interface OrderDetailReponsitory extends JpaRepository<OrderDetail, Integer>{

}
