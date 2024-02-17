package com.isaachome.job.service.controller;

import com.isaachome.job.service.entity.Company;
import com.isaachome.job.service.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {
    private  final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // get All company
    @GetMapping
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }
    // get Company by ID
    // create new Company
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company data){
     return  new ResponseEntity<>(companyService.createCompany(data), HttpStatus.CREATED);
    }
    // update Company By ID
    // delete Company
}
