package ru.belov.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belov.base.controller.BaseController;
import ru.belov.dto.PetDto;
import ru.belov.entity.PetEntity;
import ru.belov.service.PetService;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pet-service")
public class PetController extends BaseController<PetEntity, PetDto> {

    private final PetService petService;

    public PetController(PetService baseService) {
        super(baseService);
        this.petService = baseService;
    }

//    @PostMapping
//    public ResponseEntity<UUID> save(@RequestBody PetDto dto){
//        return ResponseEntity.ok().body(petService.save(dto));
//    }
//
//    @PutMapping
//    public ResponseEntity<UUID> update(@RequestBody PetDto dto){
//        return ResponseEntity.ok().body(petService.update(dto));
//    }
//
//    @PostMapping("/{id}")
//    public ResponseEntity<PetDto> getById(@PathVariable UUID id){
//        return ResponseEntity.ok().body(petService.findById(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id){
//        petService.delete(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/find-all")
//    public ResponseEntity<List<PetDto>> findAll(){
//        return ResponseEntity.ok().body(petService.findAll());
//    }
//
//    @PostMapping("/find-all-pageable")
//    public ResponseEntity<Page<PetDto>> findAll(Pageable pageable){
//        return ResponseEntity.ok().body(petService.findAllPageable(pageable));
//    }
//
//    @PostMapping("/save-all")
//    public ResponseEntity<Void> saveAll(@RequestBody List<PetDto> dtos){
//        petService.saveAll(dtos);
//        return ResponseEntity.ok().build();
//    }

}
