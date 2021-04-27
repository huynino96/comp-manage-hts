package com.example.compmanage.controller;

import com.example.compmanage.exception.ResourceNotFoundException;
import com.example.compmanage.models.Company;
import com.example.compmanage.repository.CompanyRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public List<Company> getCompany(){
        return companyRepository.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity< Company > getCompanyById(
            @PathVariable(value = "id") Long companyId) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + companyId));
        return ResponseEntity.ok().body(company);
    }

    @PostMapping("/companies")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity <Company> updateCompany(
            @PathVariable(value = "id") Long companyId,
            @Valid @RequestBody Company companyDetails) throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + companyId));
        company.setCompanyName(companyDetails.getCompanyName());
        company.setComputer(companyDetails.getComputer());
        final Company updatedCompany = companyRepository.save(company);
        return ResponseEntity.ok(updatedCompany);
    }


}
