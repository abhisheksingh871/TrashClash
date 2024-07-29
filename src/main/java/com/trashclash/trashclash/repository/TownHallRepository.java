package com.trashclash.trashclash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trashclash.trashclash.entities.TownHall;

public interface TownHallRepository extends JpaRepository<TownHall, Long> {
}