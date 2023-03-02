package edu.poly.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.StorageService;

@Controller
@RequestMapping("admin/products")
public class ProductController {
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	
	@Autowired
	StorageService storageService;
	
	//hien thi danh sach cac danh muc cho sp
	@ModelAttribute("categories")
	public List<CategoryDto> getCategories(){

		return categoryService.findAll().stream().map(item->{
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	

	
	@GetMapping("add")
	public String add(Model model) {
		ProductDto dto = new ProductDto();
		dto.setIsEdit(false);
		model.addAttribute("product",dto );
		
		return "admin/products/addOrEdit";
	}
	
	
	//productId la bien trung` voi trong pathVariable de luu thong tin do nguoi dung truyen den
	//va truyen vao fimdById de tra laij thong tin cho category
	//tao ra dto, neu co thong tin tra ve thi lay thong tin  thuc hien copy dto 
	//truyen dto len form
	
	@GetMapping("edit/{productId}")
	public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId) {
		 
		Optional<Product> opt = productService.findById(productId);
			ProductDto dto = new ProductDto();
			
		if(opt.isPresent()) {
			Product entity = opt.get();
		
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);//che do chinh sua thong tin
			
			dto.setCategoryId(entity.getCategory().getCategoryId());
			
			model.addAttribute("product", dto);
			
			return new ModelAndView("admin/products/addOrEdit", model);
		}
		
		
		model.addAttribute("message", "Update Faill");
		return new ModelAndView("forward:/admin/products", model);
	
	}
	
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("product") ProductDto dto,
			BindingResult result) {
		//tao doi tuong entity
		//su dung copyProperties de copy du lieu dto(nguoi dung nhap vao) vao` entity(DB)
		//goi method save de luu du lieu
		
		if(result.hasErrors()) {
			//kiem tra validation
			return new ModelAndView("admin/products/addOrEdit");
		}
		
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		
		Category category = new Category();
		category.setCategoryId(dto.getCategoryId());
		entity.setCategory(category);
		
		if(!dto.getImageFile().isEmpty()) {
			UUID uuid=UUID.randomUUID();
			String uuString = uuid.toString();
			
			entity.setImage(storageService.getStorageFileName(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), entity.getImage());
			
		}
		
		productService.save(entity);
		
		model.addAttribute("message", "Save Succes");
		return new ModelAndView("forward:/admin/products", model);
	}
	
	@GetMapping("delete/{productId}")
	public ModelAndView delete(ModelMap model,@PathVariable("productId") Long productId) throws IOException {
	
		Optional<Product> opt = productService.findById(productId);
		
		if(opt.isPresent()) {
			if(!StringUtils.isEmpty(opt.get().getImage()) ) {
				storageService.delete(opt.get().getImage());
			}
			productService.delete(opt.get());
			model.addAttribute("message", "Delete success");
		}else {
			model.addAttribute("message", "Product is not Found");
		}		
		return new ModelAndView("forward:/admin/products",model);
	}
	
	
	
	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
		Resource file = storageService.loadAsResource(filename);
		
		return 	ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
				"attachment; filename=\"" + file.getFilename()+"\"").body(file);
	}
	
	
	@RequestMapping("")
	public String list(ModelMap model, @RequestParam("field") Optional<String> field) {
		//su dung method findAll de tra ve ds trong CSDL
//	List<Product> list= 	productService.findAll();
	Sort sort = Sort.by(Direction.DESC, field.orElse("nameProduct"));
	model.addAttribute("field", field.orElse("price").toUpperCase());
	List<Product> prod = productService.findAll(sort);
//	model.addAttribute("prod",prod);
	model.addAttribute("products", prod);
		
		return "admin/products/list";
	}
	
	
	@GetMapping("search")
	public String search(ModelMap model,
			@RequestParam(name = "nameProduct", required = false) String nameProduct) {
		
		List<Product> list = null;
		
		if(StringUtils.hasText(nameProduct)) {
			list = productService.findByNameProductContaining(nameProduct);
		}else {
			list = productService.findAll();
		}
		model.addAttribute("products",list);
		
		return "admin/products/list";
	}
	

	
	
}











