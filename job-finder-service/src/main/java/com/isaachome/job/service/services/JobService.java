package com.isaachome.job.service.services;

import com.isaachome.job.service.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> getAllJob();
    Job getJobById(long id);
    Job createJob(Job data);
    Job updateJob(long jobId,Job job);
    boolean deleteJobById(long jobId);
}
