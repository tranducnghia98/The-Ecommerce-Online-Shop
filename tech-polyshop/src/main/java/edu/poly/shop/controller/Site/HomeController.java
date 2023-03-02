package edu.poly.shop.controller.Site;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;

@Controller

public class HomeController {

	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("home")
public String index(ModelMap model) {
	
		if(null != productService.findAll() && 0 != productService.findAll().size()) {
//			System.out.println("total item in product"+ productService.findAll().size());
			
			model.put("products", productService.findAll());
		}else {
			System.out.println("Fail");
		}
		
	return "site/home";
}
	
	@GetMapping("home/page")
	public String paginate (Model model, @RequestParam("page") Optional<Integer>page,
			@RequestParam("size")Optional<Integer> size
			) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		Pageable pageable = PageRequest.of(currentPage, pageSize);
		Page<Product> product = productService.findAll(pageable);
		model.addAttribute("products",product);
		return "/home";
	}
	
}




















