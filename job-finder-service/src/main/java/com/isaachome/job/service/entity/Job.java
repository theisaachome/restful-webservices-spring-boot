package com.isaachome.job.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "JOB_TITLE",nullable = false)
    private String jobTitle;


    @Column(name = "JOB_DESCRIPTION",nullable = false)
    private String jobDescription;


    @Column(name = "JOB_TYPE",nullable = false)
    private String jobType;

    @Column(name = "MIN_SALARY",nullable = false)
    private String minSalary;
    @Column(name = "MAX_SALARY",nullable = false)
    private String maxSalary;
    @Column(name = "LOCATION",nullable = false)
    private String location;

    @Column(name = "POST_DATE",nullable = false)
    private LocalDate postDate;

    @Column(name = "ISACTIVE")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
}
