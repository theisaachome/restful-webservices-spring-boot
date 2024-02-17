package com.isaachome.job.service.services;

import com.isaachome.job.service.entity.Job;
import com.isaachome.job.service.exception.ResourceNotFoundException;
import com.isaachome.job.service.repos.CompanyRepos;
import com.isaachome.job.service.repos.JobRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobServiceImpl implements JobService{
    private final JobRepos jobRepos;
    private  final CompanyRepos companyRepos;
    @Override
    public List<Job> getAllJob() {
        return jobRepos.findAll();
    }

    @Override
    public Job getJobById(long id) {
        return jobRepos.findById(id).orElseThrow(()-> new RuntimeException("No Resource Found!"));
    }

    @Override
    public Job createJob(Job data) {
        // find Company from Database
        var company = companyRepos.findById(data.getCompany().getId()).orElseThrow(()-> new ResourceNotFoundException("Company","ID",data.getCompany().getId()));
        var job = new Job();
        job.setJobTitle(data.getJobTitle());
        job.setJobDescription(data.getJobDescription());
        job.setCompany(company);
        company.addJob(job);
        job.setJobType(data.getJobType());
        job.setActive(data.isActive());
        job.setLocation(data.getLocation());
        job.setMinSalary(data.getMinSalary());
        job.setMaxSalary(data.getMaxSalary());
        job.setPostDate(LocalDate.now());
        job.setActive(true);
       return jobRepos.save(job);
    }

    @Override
    public Job updateJob(long jobId, Job data) {
        var job = jobRepos.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","ID",jobId));
        job.setJobTitle(data.getJobTitle());
        job.setJobDescription(data.getJobDescription());
        job.setJobType(data.getJobType());
        job.setActive(data.isActive());
        job.setLocation(data.getLocation());
        job.setMinSalary(data.getMinSalary());
        job.setMaxSalary(data.getMaxSalary());
        job.setPostDate(LocalDate.now());
        job.setActive(data.isActive());
        return jobRepos.save(job);
    }

    // set IsActive false for delete operation
    // physical deletion is not encouraged.

    @Override
    public boolean deleteJobById(long jobId) {
        var job = jobRepos.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job","ID",jobId));
        if(job!=null){
            job.setActive(false);
            jobRepos.save(job);
            return true;
        }else {
            return  false;
        }

    }
}
