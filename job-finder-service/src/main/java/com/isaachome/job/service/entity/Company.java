package com.isaachome.job.service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "COMPANY_DESCRIPTION",nullable = false)
    private String companyDescription;

    @OneToMany(orphanRemoval = true,mappedBy = "company")
    private List<Job> jobs;

}
