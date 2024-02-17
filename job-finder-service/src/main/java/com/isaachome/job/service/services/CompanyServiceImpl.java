package com.isaachome.job.service.services;

import com.isaachome.job.service.entity.Company;
import com.isaachome.job.service.exception.ResourceNotFoundException;
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
        return companyRepos.findById(id).orElseThrow(()-> new ResourceNotFoundException("Company","ID",id));
    }

    @Override
    public Company createCompany(Company data) {
        var company = new Company();
        company.setCompanyName(data.getCompanyName());
        company.setAboutCompany(data.getAboutCompany());
        return companyRepos.save(company);
    }

    @Override
    public Company updateCompany(long jobId, Company data) {
        var company = companyRepos.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Company","ID",jobId));
        company.setCompanyName(data.getCompanyName());
        company.setAboutCompany(data.getAboutCompany());
        var updatedCompany= companyRepos.save(company);
        return  updatedCompany;
    }

    @Override
    public boolean deleteById(long jobId) {
        var company = companyRepos.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Company","ID",jobId));
        if (company !=null){
            companyRepos.delete(company);
            return true;
        }
        return false;
    }
}
