package com.isaachome.balance.repo;

import java.util.List;

import com.isaachome.balance.entity.BalanceDetail;

public interface BalanceDetailRepo extends BaseRepository<BalanceDetail,Long> {

	List<BalanceDetail> findByBalanceId(long id );
}
