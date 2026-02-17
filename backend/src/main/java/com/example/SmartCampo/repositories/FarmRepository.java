package com.example.SmartCampo.repositories;

import com.example.SmartCampo.entities.Farm;  // IMPORT CORRETO: Farm, não Animal
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
    // Aqui você pode adicionar métodos personalizados se precisar
    // Exemplo: List<Farm> findByNameContaining(String name);
}