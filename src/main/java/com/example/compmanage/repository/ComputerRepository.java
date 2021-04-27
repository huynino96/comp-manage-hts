package com.example.compmanage.repository;

import com.example.compmanage.models.Company;
import com.example.compmanage.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {
    List<Computer> findByCompanyId(Long computerId);
    Optional<Computer> findByIdAndCompanyId(Long id, Long companyId);
}
