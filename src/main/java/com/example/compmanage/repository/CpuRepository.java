package com.example.compmanage.repository;

import com.example.compmanage.models.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CpuRepository extends JpaRepository<Cpu, Long> {
    List findByComputerId(Long studentId);
}
