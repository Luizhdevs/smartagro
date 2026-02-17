package com.example.SmartCampo.services;

import com.example.SmartCampo.dto.AnimalDTO;
import com.example.SmartCampo.entities.Animal;
import com.example.SmartCampo.entities.Farm;
import com.example.SmartCampo.entities.SituacaoAnimal;
import com.example.SmartCampo.repositories.AnimalRepository;
import com.example.SmartCampo.repositories.FarmRepository;
import com.example.SmartCampo.services.exceptions.DatabaseException;
import com.example.SmartCampo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private FarmRepository farmRepository;

    @Transactional(readOnly = true)
    public Page<AnimalDTO> findAllPaged(Pageable pageable) {
        Page<Animal> list = repository.findAll(pageable);
        return list.map(x -> new AnimalDTO(x));
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> findAll() {
        List<Animal> list = repository.findAll();
        return list.stream().map(x -> new AnimalDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AnimalDTO findById(Long id) {
        Optional<Animal> obj = repository.findById(id);
        Animal entity = obj.orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado. Id: " + id));
        return new AnimalDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> findBySituacao(SituacaoAnimal situacao) {
        List<Animal> list = repository.findBySituacao(situacao);
        return list.stream().map(x -> new AnimalDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public AnimalDTO insert(AnimalDTO dto) {
        Animal entity = new Animal();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new AnimalDTO(entity);
    }

    @Transactional
    public AnimalDTO update(Long id, AnimalDTO dto) {
        try {
            Animal entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new AnimalDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Animal não encontrado. Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Animal não encontrado. Id: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade - animal possui histórico ou filhos relacionados");
        }
    }

    // Métodos de negócio específicos
    @Transactional
    public AnimalDTO venderAnimal(Long id, String comprador, Double valor, LocalDate dataVenda) {
        try {
            Animal entity = repository.getReferenceById(id);
            entity.vender(comprador, valor, dataVenda);
            entity = repository.save(entity);
            return new AnimalDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Animal não encontrado. Id: " + id);
        }
    }

    @Transactional
    public AnimalDTO registrarObito(Long id, LocalDate dataObito, String causa) {
        try {
            Animal entity = repository.getReferenceById(id);
            entity.registrarObito(dataObito, causa);
            entity = repository.save(entity);
            return new AnimalDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Animal não encontrado. Id: " + id);
        }
    }

    @Transactional
    public AnimalDTO transferirAnimal(Long id, Long novaFarmId, LocalDate dataTransferencia) {
        try {
            Animal entity = repository.getReferenceById(id);
            Farm novaFarm = farmRepository.getReferenceById(novaFarmId);
            entity.transferir(novaFarm, dataTransferencia);
            entity = repository.save(entity);
            return new AnimalDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Animal ou Fazenda não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> findAnimaisAtivosNaFazenda(Long farmId) {
        List<Animal> list = repository.findAnimaisAtivosNaFazenda(farmId);
        return list.stream().map(x -> new AnimalDTO(x)).collect(Collectors.toList());
    }

    private void copyDtoToEntity(AnimalDTO dto, Animal entity) {
        // Campos básicos
        entity.setName(dto.getName());
        entity.setSpecies(dto.getSpecies());
        entity.setTipo(dto.getTipo());
        entity.setBreed(dto.getBreed());
        entity.setBirthDate(dto.getBirthDate());
        entity.setWeight(dto.getWeight());
        entity.setSituacao(dto.getSituacao() != null ? dto.getSituacao() : SituacaoAnimal.ATIVO);

        // Campos de situação
        entity.setDataVenda(dto.getDataVenda());
        entity.setDataObito(dto.getDataObito());
        entity.setDataTransferencia(dto.getDataTransferencia());
        entity.setObservacaoSituacao(dto.getObservacaoSituacao());
        entity.setComprador(dto.getComprador());
        entity.setValorVenda(dto.getValorVenda());

        // Relacionamentos com getReferenceById (mais eficiente, não busca do banco)
        try {
            if (dto.getMotherId() != null) {
                Animal mother = repository.getReferenceById(dto.getMotherId());
                entity.setMother(mother);
            } else {
                entity.setMother(null);
            }

            if (dto.getFatherId() != null) {
                Animal father = repository.getReferenceById(dto.getFatherId());
                entity.setFather(father);
            } else {
                entity.setFather(null);
            }

            if (dto.getCurrentFarmId() != null) {
                Farm farm = farmRepository.getReferenceById(dto.getCurrentFarmId());
                entity.setCurrentFarm(farm);
            } else {
                entity.setCurrentFarm(null);
            }
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entidade relacionada não encontrada");
        }
    }
}