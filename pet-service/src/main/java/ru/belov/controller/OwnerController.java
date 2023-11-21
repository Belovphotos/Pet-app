package ru.belov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.belov.base.controller.BaseController;
import ru.belov.dto.owner.FullOwnerDto;
import ru.belov.dto.owner.OwnerDto;
import ru.belov.entity.OwnerEntity;
import ru.belov.service.OwnerService;

import java.util.UUID;

@RestController
@RequestMapping("/pet-service/owners")
public class OwnerController extends BaseController<OwnerEntity, OwnerDto> {
    private final OwnerService ownerService;

    public OwnerController(OwnerService baseService) {
        super(baseService);
        this.ownerService = baseService;
    }

    @GetMapping("/find-owner-with-pets")
    public ResponseEntity<FullOwnerDto> findOwnerWithPets(@RequestParam UUID ownerId){
        return ResponseEntity.ok().body(ownerService.findAllOwnersWithPets(ownerId));
    }
}
