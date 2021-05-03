package com.example.compmanage.repository;

import com.example.compmanage.models.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RamRepository extends JpaRepository<Ram, Long> {
    List findByComputerId(Long computerId);
}
