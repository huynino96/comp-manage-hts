package com.example.compmanage.repository;

import com.example.compmanage.models.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MouseRepository extends JpaRepository<Mouse, Long> {
    List findByComputerId(Long computerId);
}
