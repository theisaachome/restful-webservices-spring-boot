package com.isaachome.balance.repo;
import java.util.List;

import com.isaachome.balance.entity.BalanceDetails;

public interface BalanceDetailsRepo extends BaseRepository<BalanceDetails,Long> {
	List<BalanceDetails> findByBalanceId(long id );
}
