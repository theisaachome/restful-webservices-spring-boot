package com.isaachome.balance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.isaachome.balance.entity.Category;
import com.isaachome.balance.entity.Type;
import com.isaachome.balance.repo.CategoryRepo;

@Service
public class CategoryService extends AbstractService<Category, Integer>{

	public CategoryService(CategoryRepo repo) {
		super(repo);
	}

	@Override
	public Category update(Integer id, Category t) {
		Category result = findById(id);
		result.setName(t.getName());
		result.setType(t.getType());
		return result;
	}
	

	public List<Category> search(Type type, String name) {
		StringBuffer sb = new StringBuffer("SELECT c FROM Category c WHERE 1=1");
		Map<String, Object> params = new HashMap<>();
		if(null!=type) {
			sb.append("and c.type =type");
			params.put("type", type);
		}
		
		if(StringUtils.hasLength(name)) {
			sb.append(" and lower(c.name) like lower(:name)");
			params.put("name", name.concat("%"));
		}
		
		return search(sb.toString(), params);
	}

}
