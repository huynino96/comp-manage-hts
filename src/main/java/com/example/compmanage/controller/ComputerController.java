package com.example.compmanage.controller;

import com.example.compmanage.exception.ResourceNotFoundException;
import com.example.compmanage.models.Computer;
import com.example.compmanage.repository.CompanyRepository;
import com.example.compmanage.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("/api")
public class ComputerController {
    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company/{companyId}/computer")
    public List<Computer> getComputerByCompany(@PathVariable(value = "companyId") Long computerId) {
        return computerRepository.findByCompanyId(computerId);
    }

    @PostMapping("/company/{companyId}/computer")
    public Computer createComputer(@PathVariable(value = "companyId") Long companyId,
                               @Valid @RequestBody Computer computer) throws ResourceNotFoundException {
        return companyRepository.findById(companyId).map(company -> {
                computer.setCompany(company);
        return computerRepository.save(computer);
        }).orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }
}
