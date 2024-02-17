package com.isaachome.job.service.controller;

import com.isaachome.job.service.entity.Job;
import com.isaachome.job.service.services.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // get All Jobs
    @GetMapping
    public List<Job> getAllJob(){
        return  jobService.getAllJob();
    }
    // get Job byId
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable("id") long jobId){
        return new ResponseEntity<>(jobService.getJobById(jobId), HttpStatus.OK);
    }
    // create new Job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job data){
        return  new ResponseEntity<>(jobService.createJob(data),HttpStatus.CREATED);
    }
    // update Job by ID
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id")long jobId,@RequestBody Job data){
        return new ResponseEntity<>(jobService.updateJob(jobId,data),HttpStatus.OK);
    }
    //
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable("id")long jobId){
        var isDeleted=jobService.deleteJobById(jobId);
        if(isDeleted){
            return  new ResponseEntity<>("Content Deleted",HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("No Content Deleted",HttpStatus.OK);
        }

    }

}
