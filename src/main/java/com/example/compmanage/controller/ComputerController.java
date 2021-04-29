package com.example.compmanage.controller;

import com.example.compmanage.exception.ResourceNotFoundException;
import com.example.compmanage.models.Computer;
import com.example.compmanage.repository.CompanyRepository;
import com.example.compmanage.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PutMapping("/company/{companyId}/computer/{computerId}")
    public Computer updateComputer(@PathVariable(value = "companyId") Long companyId,
                               @PathVariable(value = "computerId") Long computerId, @Valid @RequestBody Computer computerRequest)
            throws ResourceNotFoundException {
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company ID not found");
        }
        return computerRepository.findById(computerId).map(computer -> {
                computer.setComputerName(computerRequest.getComputerName());
                computer.setUser(computerRequest.getUser());
        return computerRepository.save(computer);
        }).orElseThrow(() -> new ResourceNotFoundException("course id not found"));
    }

    @DeleteMapping("/company/{companyId}/computer/{computerId}")
    public ResponseEntity< ? > deleteComputer(@PathVariable(value = "companyId") Long companyId,
                                              @PathVariable(value = "computerId") Long computerId) throws ResourceNotFoundException {
        return computerRepository.findByIdAndCompanyId(computerId, companyId).map(computer -> {
            computerRepository.delete(computer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Computer not found with id " + computerId + " and Company ID " + companyId));
    }

}
