package com.example.SmartCampo.repositories;

import com.example.SmartCampo.entities.Animal;
import com.example.SmartCampo.entities.SituacaoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findBySituacao(SituacaoAnimal situacao);

    @Query("SELECT a FROM Animal a WHERE a.situacao = 'ATIVO' AND a.currentFarm.id = :farmId")
    List<Animal> findAnimaisAtivosNaFazenda(@Param("farmId") Long farmId);

    @Query("SELECT a FROM Animal a WHERE a.situacao IN ('VENDIDO', 'MORTO')")
    List<Animal> findAnimaisInativos();

    List<Animal> findByMotherId(Long motherId);

    List<Animal> findByFatherId(Long fatherId);

    List<Animal> findByCurrentFarmId(Long farmId);
}