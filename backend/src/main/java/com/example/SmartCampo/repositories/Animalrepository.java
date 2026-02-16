package com.example.SmartCampo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartCampo.entities.Animal;

@Repository
public interface Animalrepository extends JpaRepository<Animal, Long> {

}
