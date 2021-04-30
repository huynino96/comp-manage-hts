package com.example.compmanage.repository;

import com.example.compmanage.models.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouseRepository extends JpaRepository<Mouse, Long> {
}
