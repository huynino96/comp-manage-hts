package com.example.compmanage.repository;

import com.example.compmanage.models.Gpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GpuRepository extends JpaRepository<Gpu, Long> {
    List findByComputerId(Long computerId);
}
