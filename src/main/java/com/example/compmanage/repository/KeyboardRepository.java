package com.example.compmanage.repository;

import com.example.compmanage.models.Keyboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyboardRepository extends JpaRepository<Keyboard, Long> {
    //List findByComputerId(Long computerId);
}
