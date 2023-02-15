package ru.arrowin.bedstoremanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.services.BedService;

@RestController
@RequestMapping("/furniture")
@Tag(name = "Сделка", description = "Подсчет оплаты за выполненную работу")
public class FurnitureController {

    private final BedService bedService;


    public FurnitureController(BedService bedService) {
        this.bedService = bedService;
    }

    @PostMapping("create")
    public ResponseEntity<Bed> addBed(@RequestBody Bed bed) {
        bedService.add(bed);
        return ResponseEntity.ok(bed);
    }

    @GetMapping("getsum")
    public ResponseEntity<Double> getAllSalaryInDay(){
        double sum = bedService.allCostInDay();
        return ResponseEntity.ok(sum);
    }

}
