package com.example.compmanage.repository;

import com.example.compmanage.models.Mainboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainboardRepository extends JpaRepository<Mainboard, Long> {
    List findByComputerId(Long computerId);
}
