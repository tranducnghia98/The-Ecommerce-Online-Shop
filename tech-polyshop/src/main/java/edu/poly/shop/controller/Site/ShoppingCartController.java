package edu.poly.shop.controller.Site;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CartItem;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.ShoppingCartService;

@Controller
@RequestMapping("site")
public class ShoppingCartController {

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingCartService shoppingCartService;
	
	
	@GetMapping("views")
	public String vierwCarts(Model model) {
		model.addAttribute("CART", shoppingCartService.getAllItem());
		model.addAttribute("TOTAL", shoppingCartService.getAmount());
		return "site/cartItem";
	}
	
	
	@GetMapping("add/{productId}")
	public String addCart(@PathVariable("productId") Long productId) {
		 
		Optional<Product> opt = productService.findById(productId);
		if(opt != null) {
			CartItem item = new CartItem();
			item.setProductId(opt.get().getProductId());
			item.setNameProduct(opt.get().getNameProduct());
			item.setQuantity(1);
			item.setUnitPrice(opt.get().getUnitPrice());
			shoppingCartService.add(item);

		}
 		
		return "forward:/site/views";
	}
	
	@GetMapping("clear")
	public String clearCart() {
		shoppingCartService.clear();
		return "forward:/site/views";
	}
	
	@GetMapping("delete/{productId}")
	public String deleteCart(@PathVariable("productId") Long productId)  {
		shoppingCartService.remove(productId);
		return "forward:/site/views";
	}
	
	
	
	
	
	
	
	
	
	
	
}
