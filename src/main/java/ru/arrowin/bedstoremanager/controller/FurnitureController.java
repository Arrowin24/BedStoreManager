package ru.arrowin.bedstoremanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.bedstoremanager.models.Furniture;
import ru.arrowin.bedstoremanager.services.FurnitureService;

@RestController
@RequestMapping("/furniture")
@Tag(name = "Сделка", description = "Подсчет оплаты за выполненную работу")
public class FurnitureController {

    private final FurnitureService furnitureService;


    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @PostMapping("create")
    public ResponseEntity<Furniture> addFurniture(@RequestBody Furniture furniture) {
        furnitureService.add(furniture);
        return ResponseEntity.ok(furniture);
    }

    @GetMapping("getsum")
    public ResponseEntity<Double> getAllSalaryInDay(@RequestParam double cost){
        furnitureService.allCostInDay();
        return ResponseEntity.ok(cost);
    }

}
