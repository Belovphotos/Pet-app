package ru.common.controller;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.common.dto.BaseDto;
import ru.common.entity.BaseEntity;
import ru.common.service.BaseService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BaseController<E extends BaseEntity, D extends BaseDto> {
    protected final BaseService<E, D> baseService;

    @PostMapping("/find-all-pageable")
    public ResponseEntity<Page<D>> findAllPageable(Pageable pageable){
       return ResponseEntity.ok()
               .body(baseService.findAllPageable(pageable));
   }

    @PostMapping("/find-all")
    public ResponseEntity<List<D>> findAll(){
        return ResponseEntity.ok()
                .body(baseService.findAll());
    }

   @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable("id") @Nonnull UUID id){
       return ResponseEntity.ok().body(baseService.findById(id));
   }

   @PostMapping
    public ResponseEntity<UUID> save(@RequestBody D dto){
       return ResponseEntity.ok().body(baseService.save(dto));
   }

   @PutMapping
    public ResponseEntity<?> update(@RequestBody D dto){
       try {
       return ResponseEntity.ok().body(baseService.update(dto));
   } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(e);
           }
       }

       @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Nonnull UUID id){
       baseService.delete(id);
       return ResponseEntity.status(HttpStatus.OK).build();
       }
}
