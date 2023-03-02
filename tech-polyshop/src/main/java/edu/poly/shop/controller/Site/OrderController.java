package edu.poly.shop.controller.Site;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CartItem;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.CustomerService;
import edu.poly.shop.service.OrderDetailService;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.ShoppingCartService;

@Controller
@RequestMapping("site")
public class OrderController {

	@Autowired
	HttpSession session;
	@Autowired
	OrderService orderService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	ProductService productService;
	
	@GetMapping("order/{productId}")
public String order(Model model, @PathVariable("productId") Long productId) {
		
		Optional<Product> product = productService.findById(productId);
		
		double amount = product.get().getUnitPrice()*product.get().getQuantity();
		String customerId = (String)session.getAttribute("nameCustomer");
		if(customerId ==  null) {
			return "redirect:/alogin";
		}
		Customer cus = customerService.findByName(customerId);
		
		Order order = new Order();
		order.setAmount(amount);
		order.setOrderDate(new Date());
		order.setStatus((short) 0);
		order.setCustomer(cus);
		orderService.save(order);
		
		Integer orderId = order.getOrderId();
		model.addAttribute("orderId",orderId);
		model.addAttribute("productId",productId);
		System.out.println(orderId);
		System.out.println(productId);
		
		
		return "site/products/detail";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}


















