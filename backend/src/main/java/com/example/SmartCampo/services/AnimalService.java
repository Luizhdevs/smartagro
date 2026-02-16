package com.example.SmartCampo.services;

import com.example.SmartCampo.dto.AnimalDTO;
import com.example.SmartCampo.entities.Animal;
import com.example.SmartCampo.repositories.Animalrepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private Animalrepository animalrepository;

    @Transactional
    public Page<AnimalDTO> findAllPaged(Pageable pageable) {
        Page<Animal> list = animalrepository.findAll(pageable);
        return list.map(x -> new AnimalDTO(x));
    }
}
