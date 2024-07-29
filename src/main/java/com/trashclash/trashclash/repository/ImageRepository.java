package com.trashclash.trashclash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trashclash.trashclash.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}