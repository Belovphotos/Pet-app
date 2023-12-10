package ru.belov.controller;


import org.springframework.web.bind.annotation.*;
import ru.belov.filter.PetEntityFilter;
import ru.common.controller.BaseController;
import ru.belov.dto.PetDto;
import ru.belov.entity.PetEntity;
import ru.belov.service.PetService;

@RestController
@RequestMapping("/pet-service/pets")
public class PetController extends BaseController<PetEntity, PetDto, PetEntityFilter> {

    private final PetService petService;

    public PetController(PetService baseService) {
        super(baseService);
        this.petService = baseService;
    }

}
