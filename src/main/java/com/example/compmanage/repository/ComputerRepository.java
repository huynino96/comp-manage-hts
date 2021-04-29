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

    List<Computer> findByCpuId(Long computerId);
    Optional<Computer> findByIdAndCpuId(Long id, Long cpuId);

    List<Computer> findByDiskId(Long computerId);
    Optional<Computer> findByIdAndDiskId(Long id, Long diskId);
}
