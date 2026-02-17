package com.example.SmartCampo.resources;

import com.example.SmartCampo.dto.AnimalDTO;
import com.example.SmartCampo.entities.SituacaoAnimal;
import com.example.SmartCampo.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/animais")
public class AnimalResource {

    @Autowired
    private AnimalService service;

    @GetMapping
    public ResponseEntity<Page<AnimalDTO>> findAllPaged(Pageable pageable) {
        Page<AnimalDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<AnimalDTO>> findAll() {
        List<AnimalDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> findById(@PathVariable Long id) {
        AnimalDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/situacao/{situacao}")
    public ResponseEntity<List<AnimalDTO>> findBySituacao(@PathVariable SituacaoAnimal situacao) {
        List<AnimalDTO> list = service.findBySituacao(situacao);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fazenda/{farmId}/ativos")
    public ResponseEntity<List<AnimalDTO>> findAnimaisAtivosNaFazenda(@PathVariable Long farmId) {
        List<AnimalDTO> list = service.findAnimaisAtivosNaFazenda(farmId);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> insert(@RequestBody AnimalDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AnimalDTO> update(@PathVariable Long id, @RequestBody AnimalDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints específicos de negócio
    @PutMapping(value = "/{id}/vender")
    public ResponseEntity<AnimalDTO> venderAnimal(
            @PathVariable Long id,
            @RequestParam String comprador,
            @RequestParam Double valor,
            @RequestParam String dataVenda) {
        LocalDate date = LocalDate.parse(dataVenda);
        AnimalDTO dto = service.venderAnimal(id, comprador, valor, date);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}/obito")
    public ResponseEntity<AnimalDTO> registrarObito(
            @PathVariable Long id,
            @RequestParam String dataObito,
            @RequestParam String causa) {
        LocalDate date = LocalDate.parse(dataObito);
        AnimalDTO dto = service.registrarObito(id, date, causa);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}/transferir")
    public ResponseEntity<AnimalDTO> transferirAnimal(
            @PathVariable Long id,
            @RequestParam Long novaFarmId,
            @RequestParam String dataTransferencia) {
        LocalDate date = LocalDate.parse(dataTransferencia);
        AnimalDTO dto = service.transferirAnimal(id, novaFarmId, date);
        return ResponseEntity.ok().body(dto);
    }
}