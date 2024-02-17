package com.isaachome.job.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "COMPANY_NAME",nullable = false)
    private String companyName;
    @Column(name = "ABOUT_COMPANY",nullable = false)
    private String aboutCompany;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company",orphanRemoval = true)
    private List<Job> jobs= new ArrayList<>();
    public void addJob(Job job){
        this.jobs.add(job);
        job.setCompany(this);
    }
    public  void removeJob(Job job){
        job.setCompany(null);
        this.jobs.remove(job);
    }
    public void removeJobs(Job job){
        Iterator<Job> jobIterator = this.jobs.iterator();
        while (jobIterator.hasNext()){
            Job data = jobIterator.next();
            data.setCompany(null);
            jobIterator.remove();
        }
    }

}
