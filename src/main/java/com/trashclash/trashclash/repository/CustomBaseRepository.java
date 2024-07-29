package com.trashclash.trashclash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trashclash.trashclash.entities.CustomBase;

public interface CustomBaseRepository extends JpaRepository<CustomBase, Long> {
}