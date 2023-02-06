package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.repository.WorkerRepository;
import ru.arrowin.bedstoremanager.services.WorkerService;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;


    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }


    @Override
    public void create(Worker worker) {
        workerRepository.save(worker);
    }

    @Override
    public List<Worker> readAll() {
        return workerRepository.findAll();
    }


    @Override
    public Worker read(long id) {
        if (workerRepository.findById(id).isPresent()) {
            return workerRepository.findById(id).get();
        }
        throw new RuntimeException("Нет такого рабочего");
    }

    @Override
    public boolean update(Worker worker, long id) {
        if (workerRepository.existsById(id)) {
            worker.setId(id);
            workerRepository.save(worker);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (workerRepository.existsById(id)) {
            workerRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
