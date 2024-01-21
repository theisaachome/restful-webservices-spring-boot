package com.isaachome.bookstore.repos;

import com.isaachome.bookstore.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepos extends JpaRepository<Address,Long> {
}
