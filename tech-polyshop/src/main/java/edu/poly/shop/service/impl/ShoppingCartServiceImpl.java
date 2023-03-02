package edu.poly.shop.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.poly.shop.model.CartItem;
import edu.poly.shop.reponsitory.ProductReponsitory;
import edu.poly.shop.service.ShoppingCartService;


@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductReponsitory productReponsitory;
	
	
	Map<Long, CartItem> maps = new HashMap<>(); // hien thi danh sach gioi hang

//	@Override
//	public CartItem findCartById(Long productId) {
//		List< Product> list = productReponsitory.findAll();
//		
//		for (Product cartItem : list) {
//			if(productId.equals(cartItem.getProductId())) {
//				
//				return cartItem;
//			}
//		}
//		
//		return null;
//	}
	
	@Override
	public void add(CartItem item) {
		CartItem cart = maps.get(item.getProductId());

		if (cart == null) {
			maps.put(item.getProductId(), item);

		} else {
			cart.setQuantity(cart.getQuantity() + 1);
		}
	}

	@Override
	public void remove(Long productId) {
		maps.remove(productId);
	}

	@Override
	public CartItem update(Long productId, int quantity) {
		CartItem cart = maps.get(productId);
		cart.setQuantity(quantity);
		return cart;
	}
	@Override
	public void clear() {
		
		maps.clear();
	}
	
	@Override
	public Collection<CartItem> getAllItem(){
		return maps.values();
	}
	
	@Override
	public int getCount() {
		return maps.values().size();
	}
	
	@Override
	public double getAmount() {
		
		return maps.values().stream().mapToDouble(item -> item.getQuantity()*item.getUnitPrice())
																		.sum();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



















