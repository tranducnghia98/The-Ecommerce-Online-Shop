package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.CustomerService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
	@Autowired
	CustomerService customerService;

	@GetMapping("add")
	public String add(Model model) {

		model.addAttribute("customer", new CustomerDto());

		return "admin/accounts/addOrEdit";
	}
//	---------------------------------------------------------------

	@RequestMapping("")
	public String list(ModelMap model) {
		// su dung method findAll de tra ve ds trong CSDL
		List<Customer> list = customerService.findAll();

		model.addAttribute("customer", list);

		return "admin/accounts/list";
	}

//	=========================================================================================
	//username la bien trung` voi trong pathVariable de luu thong tin do nguoi dung truyen den
	//va truyen vao fimdById de tra laij thong tin cho customer
	//tao ra dto, neu co thong tin tra ve thi lay thong tin  thuc hien copy dto 
	//truyen dto len form
	
	@GetMapping("edit/{nameCustomer}")
	public ModelAndView edit(ModelMap model, @PathVariable("nameCustomer") String nameCustomer) {

		Customer customer = customerService.findByName(nameCustomer);
//		Optional<Customer> opt = customerService.findByName();
			CustomerDto dto = new CustomerDto();
			
		if(customer != null) {
			customer.getClass();
			
		
			BeanUtils.copyProperties(customer, dto);
			dto.setIsEdit(true);//che do chinh sua thong tin
			
			dto.setPassword("");
			
			
			model.addAttribute("customer", dto);
			
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}
		
		
		model.addAttribute("message", "Update Faill");
		return new ModelAndView("forward:/admin/accounts", model);
	
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {
		// tao doi tuong entity
		// su dung copyProperties de copy du lieu dto(nguoi dung nhap vao) vao`
		// entity(DB)
		// goi method save de luu du lieu

//		if (result.hasErrors()) {
//			// kiem tra validation
//			return new ModelAndView("admin/accounts/addOrEdit");
//		}

		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		entity.getRegisteredDate();
//		entity.isRole();
		customerService.save(entity);

		model.addAttribute("message", "Save Succes");
		return new ModelAndView("forward:/admin/accounts", model);
	}
	
//	=================================================
	
	@GetMapping("delete/{nameCustomer}")
	public ModelAndView delete(ModelMap model,@PathVariable("nameCustomer") String nameCustomer) {
		
		Customer customer = customerService.findByName(nameCustomer);
		if(nameCustomer.equals(customer.getNameCustomer())) {
			customerService.deleteById(customer.getCustomerId());
		}
		
		
		model.addAttribute("message", "Delete success");
		
		return new ModelAndView("forward:/admin/accounts",model);
	}
	

//	
//	
//	@GetMapping("search")
//	public String search(ModelMap model,
//			@RequestParam(name = "nameCategory", required = false) String nameCategory) {
//		
//		List<Category> list = null;
//		
//		if(StringUtils.hasText(nameCategory)) {
//			list = customerService.findByNameCategoryContaining(nameCategory);
//		}else {
//			list = customerService.findAll();
//		}
//		model.addAttribute("accounts",list);
//		
//		return "admin/accounts/search";
//	}
//	
//	@GetMapping("searchpaginated")
//	public String search(ModelMap model,
//			@RequestParam(name = "nameCategory", required = false) String nameCategory,
//			@RequestParam("page") Optional<Integer> page,
//			@RequestParam("size") Optional<Integer> size) {
//		
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//		
//		Pageable pageable = PageRequest.of(currentPage-1, pageSize,Sort.by("nameCategory"));
//		Page<Category> resultPage = null;
//		
//		
//		if(StringUtils.hasText(nameCategory)) {
//			resultPage = customerService.findByNameCategoryContaining(nameCategory,pageable);
//			model.addAttribute("nameCategory", nameCategory);
//		}else {
//			resultPage = customerService.findAll(pageable);
//		}
//	
//		
//		
//		int totalPages = resultPage.getTotalPages();		
//		if(totalPages>0) {
//			int start = Math.max(1, currentPage -2);
//			int end = Math.min(currentPage + 2, totalPages);
//			
//			if(totalPages>5) {
//				if(end == totalPages)start =  end -5;
//				else if (start == 1) end =  start +5;
//			}
//			List<Integer> pageNumber = IntStream.rangeClosed(start, end)
//					.boxed()
//					.collect(Collectors.toList());
//			model.addAttribute("pageNumber", pageNumber);
//		}
//		 
//		model.addAttribute("categoryPage",resultPage);
//		
//		return "admin/accounts/searchpaginated";
//	}
}
