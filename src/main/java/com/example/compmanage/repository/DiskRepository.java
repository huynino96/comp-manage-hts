package com.example.compmanage.repository;


import com.example.compmanage.models.Disk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiskRepository extends JpaRepository<Disk, Long> {
    List findByComputerId(Long computerId);
}
