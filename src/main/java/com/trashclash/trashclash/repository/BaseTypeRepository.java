package com.trashclash.trashclash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.trashclash.trashclash.entities.BaseType;

public interface BaseTypeRepository extends JpaRepository<BaseType, Long> {
}