package com.isaachome.balance.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.isaachome.balance.repo.BaseRepository;

public abstract class AbstractService<T,ID extends Number> {

	private BaseRepository<T, ID> repo;

	public AbstractService(BaseRepository<T, ID> repo) {
		this.repo = repo;
	}
	@Transactional
	public abstract T update (ID id,T t);
	
	public T save(T t) {
		return repo.save(t);
	}
	
	public T findById(ID id) {
		return repo.findById(id).orElseThrow();
	}
	
	protected List<T> search(String jpql,Map<String,Object> params){
		return repo.search(jpql,params);
	}
}
