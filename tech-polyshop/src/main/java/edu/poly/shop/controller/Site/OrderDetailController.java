package edu.poly.shop.controller.Site;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CartItem;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.reponsitory.ProductReponsitory;
import edu.poly.shop.service.OrderDetailService;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.ShoppingCartService;

@Controller
@RequestMapping("site")
public class OrderDetailController {

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping("orderDetail")
	public ModelAndView orderDetail (ModelMap model, @RequestParam("orderId") Integer orderId,
			@RequestParam("productId") Long productId){
		System.out.println("hAHA");
		
			System.out.println("OrderId" + orderId);
	Optional<Order> optOrder = orderService.findById(orderId);
	Order order = optOrder.get();
	double amount = order.getAmount();
			
	System.out.println(productId);
	
	Optional<Product> optProduct = productService.findById(productId);
	Product product = optProduct.get();
	
	OrderDetail detail = new OrderDetail();
	detail.setOrder(order);
	detail.setProduct(product);
	detail.setUnitPrice(product.getUnitPrice());
	detail.setQuantity((int) (amount/detail.getUnitPrice()));
	
	orderDetailService.save(detail);
	shoppingCartService.remove(productId);
	
			return new ModelAndView("redirect:/site/views");
	}
	
	
}
