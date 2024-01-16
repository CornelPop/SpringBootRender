package com.example.SpringBootRender.repository;

import com.example.SpringBootRender.model.Purse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurseRepository extends JpaRepository<Purse, Integer> {
}
