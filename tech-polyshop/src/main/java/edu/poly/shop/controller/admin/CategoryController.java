package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("add")
	public String add(Model model) {
		
		model.addAttribute("category", new CategoryDto());
		
		return "admin/categories/addOrEdit";
	}
	
	
	//categoryId la bien trung` voi trong pathVariable de luu thong tin do nguoi dung truyen den
	//va truyen vao fimdById de tra laij thong tin cho category
	//tao ra dto, neu co thong tin tra ve thi lay thong tin  thuc hien copy dto 
	//truyen dto len form
	
	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {
		
		Optional<Category> opt = categoryService.findById(categoryId);
			CategoryDto dto = new CategoryDto();
			
		if(opt.isPresent()) {
			Category entity = opt.get();
		
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);//che do chinh sua thong tin
			
			model.addAttribute("category", dto);
			
			return new ModelAndView("admin/categories/addOrEdit", model);
		}
		
		
		model.addAttribute("message", "Update Faill");
		return new ModelAndView("forward:/admin/categories", model);
	
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("category") CategoryDto dto,
			BindingResult result) {
		//tao doi tuong entity
		//su dung copyProperties de copy du lieu dto(nguoi dung nhap vao) vao` entity(DB)
		//goi method save de luu du lieu
		
		if(result.hasErrors()) {
			//kiem tra validation
			return new ModelAndView("admin/categories/addOrEdit");
		}
		
		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		
		categoryService.save(entity);
		
		model.addAttribute("message", "Save Succes");
		return new ModelAndView("forward:/admin/categories", model);
	}
	
	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model,@PathVariable("categoryId") Long categoryId) {
		
		categoryService.deleteById(categoryId);
		
		model.addAttribute("message", "Delete success");
		
		return new ModelAndView("forward:/admin/categories/search",model);
	}
	
	@RequestMapping("")
	public String list(ModelMap model) {
		//su dung method findAll de tra ve ds trong CSDL
	List<Category> list= 	categoryService.findAll();
		
	model.addAttribute("categories", list);
		
		return "admin/categories/list";
	}
	
	
	@GetMapping("search")
	public String search(ModelMap model,
			@RequestParam(name = "nameCategory", required = false) String nameCategory) {
		
		List<Category> list = null;
		
		if(StringUtils.hasText(nameCategory)) {
			list = categoryService.findByNameCategoryContaining(nameCategory);
		}else {
			list = categoryService.findAll();
		}
		model.addAttribute("categories",list);
		
		return "admin/categories/search";
	}
	
	@GetMapping("searchpaginated")
	public String search(ModelMap model,
			@RequestParam(name = "nameCategory", required = false) String nameCategory,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage-1, pageSize,Sort.by("nameCategory"));
		Page<Category> resultPage = null;
		
		
		if(StringUtils.hasText(nameCategory)) {
			resultPage = categoryService.findByNameCategoryContaining(nameCategory,pageable);
			model.addAttribute("nameCategory", nameCategory);
		}else {
			resultPage = categoryService.findAll(pageable);
		}
	
		
		
		int totalPages = resultPage.getTotalPages();		
		if(totalPages>0) {
			int start = Math.max(1, currentPage -2);
			int end = Math.min(currentPage + 2, totalPages);
			
			if(totalPages>5) {
				if(end == totalPages)start =  end -5;
				else if (start == 1) end =  start +5;
			}
			List<Integer> pageNumber = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			model.addAttribute("pageNumber", pageNumber);
		}
		 
		model.addAttribute("categoryPage",resultPage);
		
		return "admin/categories/searchpaginated";
	}
}












