package ru.arrowin.bedstoremanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.services.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerService workerService;


    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getAllWorkers() {
        return ResponseEntity.ok(workerService.readAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable int id) {
        return ResponseEntity.ok(workerService.read(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Worker> createWorker(@RequestBody Worker worker){
        workerService.create(worker);
        return ResponseEntity.ok(worker);
    }
    @DeleteMapping
    public ResponseEntity<Integer> deleteWorker(@RequestParam int id){
        workerService.delete(id);
        return ResponseEntity.ok(id);
    }


}
