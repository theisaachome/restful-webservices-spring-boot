package com.isaachome.job.service.services;

import com.isaachome.job.service.entity.Company;
import com.isaachome.job.service.repos.CompanyRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{
    private  final CompanyRepos companyRepos;

    public CompanyServiceImpl(CompanyRepos companyRepos) {
        this.companyRepos = companyRepos;
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepos.findAll();
    }

    @Override
    public Company getCompanyById(long id) {
        return null;
    }

    @Override
    public Company createCompany(Company data) {
        return null;
    }

    @Override
    public Company updateCompany(long jobId, Company data) {
        return null;
    }

    @Override
    public boolean deleteById(long jobId) {
        return false;
    }
}
