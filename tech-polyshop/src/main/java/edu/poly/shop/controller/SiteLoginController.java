//package edu.poly.shop.controller;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import edu.poly.shop.domain.Customer;
//import edu.poly.shop.model.AdminLoginDto;
//import edu.poly.shop.model.SiteLoginDto;
//import edu.poly.shop.service.AccountService;
//import edu.poly.shop.service.CustomerService;
//
//@Controller
//public class SiteLoginController {
//	@Autowired
//	private AccountService accountService;
//	@Autowired
//	private CustomerService customerService;
//	@Autowired
//	private HttpSession session;
//
//	@GetMapping("slogin")
//	public String login(ModelMap model) {
//		model.addAttribute("account", new AdminLoginDto());
//
//		return "/admin/accounts/login";
//	}
//
//	@PostMapping("slogin")
//	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("customer") SiteLoginDto dto,
//			BindingResult result) {
//		if (result.hasErrors()) {
//			return new ModelAndView("/admin/accounts/login", model);
//		}
//		Customer customer = customerService.login(dto.getNameCustomer(), dto.getPassword());
//		
//		if (customer == null) {
//			model.addAttribute("message", "Invalid username or password ");
//
//			return new ModelAndView("/admin/accounts/login", model);
//		}
//		session.setAttribute("username", customer.getNameCustomer());
//
//		Object ruri = session.getAttribute("redirect-uri");
//		if (ruri != null) {
//			session.removeAttribute("redirect:" + ruri);
//			return new ModelAndView("redirect:" + ruri);
//		}
//
//		return new ModelAndView("redirect:/home", model);
//
//	}
//}
