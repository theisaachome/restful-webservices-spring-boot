package com.isaachome.balance.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaachome.balance.entity.Balance;
import com.isaachome.balance.entity.BalanceDetail;
import com.isaachome.balance.entity.Type;
import com.isaachome.balance.repo.BalanceDetailRepo;
import com.isaachome.balance.repo.BalanceRepo;

@Service
public class BalanceService extends AbstractService<Balance, Long> {

	@Autowired
	private BalanceDetailRepo detailsRepo;

	public BalanceService(BalanceRepo repo) {
		super(repo);
	}

	@Override
	public Balance update(Long id, Balance data) {
		Balance result = findById(id);
		result.setCategory(data.getCategory());
		result.setTotal(data.getTotal());
		result.setType(data.getType());
		result.setUsedDate(data.getUsedDate());
		return result;
	}

	@Transactional
	public Balance save(Long id, List<BalanceDetail> details) {
		Balance balance = findById(id);
		for (BalanceDetail d : details) {
			if (d.getId() == 0) {
				d.setBalance(balance);
				detailsRepo.save(d);
			} else {
				if (d.isDeleted()) {
					detailsRepo.delete(d);
				} else {
					detailsRepo.save(d);
				}
			}
		}
		List<BalanceDetail> list = detailsRepo.findByBalanceId(id);
		balance.setTotal(list.stream().mapToInt(d -> d.getAmount()).sum());
		return balance;
	}

	public List<BalanceDetail> findDetails(Long id) {
		return detailsRepo.findByBalanceId(id);
	}


	public List<Balance> search(Type type, int category, LocalDate from, LocalDate to) {
		StringBuffer sb = new StringBuffer("select b from Balance b where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(null != type) {
			sb.append(" and b.type = :type");
			params.put("type", type);
		}
		
		if(category > 0) {
			sb.append(" and b.category.id = :category");
			params.put("category", category);
		}

		if(null != from) {
			sb.append(" and b.useDate >= :from");
			params.put("from", from);
		}

		if(null != to) {
			sb.append(" and b.useDate <= :to");
			params.put("to", to);
		}

		return search(sb.toString(), params);
	}

	public List<BalanceDetail> searchDetails(Type type, int category, LocalDate from, LocalDate to) {
		StringBuffer sb = new StringBuffer("select b from BalanceDetails b where 1 = 1");
		Map<String, Object> params = new HashMap<>();
		
		if(null != type) {
			sb.append(" and b.balance.type = :type");
			params.put("type", type);
		}
		
		if(category > 0) {
			sb.append(" and b.balance.category.id = :category");
			params.put("category", category);
		}

		if(null != from) {
			sb.append(" and b.balance.useDate >= :from");
			params.put("from", from);
		}

		if(null != to) {
			sb.append(" and b.balance.useDate <= :to");
			params.put("to", to);
		}

		return detailsRepo.search(sb.toString(), params);
	}


}
