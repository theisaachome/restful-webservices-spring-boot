package com.isaachome.job.service.services;

import com.isaachome.job.service.entity.Company;
import com.isaachome.job.service.entity.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();
    Company getCompanyById(long id);
    Company createCompany(Company data);
    Company updateCompany(long jobId,Company data);
    boolean deleteById(long jobId);
}
