package edu.poly.shop.service;

import java.util.Collection;

import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CartItem;

public interface ShoppingCartService {

	double getAmount();

	int getCount();

	Collection<CartItem> getAllItem();

	void clear();

	CartItem update(Long productId, int quantity);

	void remove(Long productId);

	void add(CartItem item);



	

}
