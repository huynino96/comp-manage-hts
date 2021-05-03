package com.example.compmanage.repository;

import com.example.compmanage.models.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    List findByComputerId(Long computerId);
}
