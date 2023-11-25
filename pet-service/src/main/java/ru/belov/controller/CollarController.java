package ru.belov.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.common.controller.BaseController;
import ru.belov.dto.CollarDto;
import ru.belov.entity.CollarEntity;
import ru.belov.service.CollarService;

@RestController
@RequestMapping("/pet-service/collars")
public class CollarController extends BaseController<CollarEntity, CollarDto> {

    private final CollarService collarService;

    public CollarController(CollarService baseService) {
        super(baseService);
        this.collarService = baseService;
    }
}
