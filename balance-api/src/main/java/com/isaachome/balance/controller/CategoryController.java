package com.isaachome.balance.controller;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.isaachome.balance.entity.Category;
import com.isaachome.balance.entity.Type;
import com.isaachome.balance.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController extends AbstractController<Category,Integer> {

	private CategoryService service;
	public CategoryController(CategoryService service) {
		super(service);
		this.service =service;
	}
	
	@GetMapping
	public List<Category> search(
		@RequestParam(required = false,name = "type")	Type type,
	  @RequestParam(required = false)	String name) {
		return service.search(type, name);
	}

}
