package edu.poly.shop.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CustomerService;

@Controller
public class AdminLoginController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private HttpSession session;

	@GetMapping("alogin")
	public String login(ModelMap model) {
		model.addAttribute("customer", new CustomerDto());

		return "/admin/accounts/login";
	}


	@PostMapping("alogin")
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {
		System.out.println("chaydi");
		if (result.hasErrors()) {
			
			return new ModelAndView("/admin/accounts/login", model);
		}

		Optional<Customer> customer = customerService.checklogin(dto.getNameCustomer(), dto.getPassword());

		if (customer == null) {
			model.addAttribute("message", "Invalid username or password ");

			return new ModelAndView("/admin/accounts/login", model);
		}
		session.setAttribute("nameCustomer", customer.get().getNameCustomer());
		if(customer.get().isRole()== false) {
			System.out.println("xin may`");
//			session.setAttribute("nameCustomer", customer.get().getNameCustomer());
			return new ModelAndView("redirect:/home", model);
		}
		
		System.out.println("login di");
		Object ruri = session.getAttribute("redirect-uri");

		if (ruri != null) {		
			session.removeAttribute("redirect:" + ruri);
			
			return new ModelAndView("redirect:" + ruri);
		}

		return new ModelAndView("forward:/admin/categories", model);

	}

	

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("nameCustomer");
		return "redirect:/alogin";
	}

}
