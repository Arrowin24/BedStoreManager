package ru.arrowin.bedstoremanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.SalaryBed;
import ru.arrowin.bedstoremanager.services.SalaryBedService;

@RestController
@RequestMapping("/salary")
@Tag(name = "Сделка", description = "Подсчет оплаты за выполненную работу")
public class SalaryBedController {


     private final SalaryBedService salaryBedService;

    public SalaryBedController(SalaryBedService salaryBedService) {
        this.salaryBedService = salaryBedService;
    }

    @PostMapping("create")
        public ResponseEntity<SalaryBed> addBed(@RequestBody SalaryBed salaryBed) {
            salaryBedService.add(salaryBed);
            return ResponseEntity.ok(salaryBed);
        }

        @GetMapping("getsum")
        public ResponseEntity<Double> getAllSalaryInDay(){
            double sum = salaryBedService.allSalaryToDay();
            return ResponseEntity.ok(sum);
        }
}
