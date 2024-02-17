package com.isaachome.job.service.repos;

import com.isaachome.job.service.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepos extends JpaRepository<Company,Long> {
}
