package ru.arrowin.bedstoremanager.services.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Worker;

import ru.arrowin.bedstoremanager.models.answers.WorkAnswer;
import ru.arrowin.bedstoremanager.repository.WorkerRepository;
import ru.arrowin.bedstoremanager.services.WorkerService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final Map<Long, WorkAnswer> creatingSteps = new HashMap<>();

    @Autowired
    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public void startCreateWorkerStep(long id) {
        creatingSteps.put(id, new WorkAnswer(0, new Worker(id, "user", "user", "worker")));
    }

    public boolean addWorkerNameStep(long id, String name) {
        if (!creatingSteps.containsKey(id)) {
            throw new RuntimeException("Что-то не так с добавлением имени, вернитесь обратно");
        }
        if (creatingSteps.get(id).getStepNum() == 0) {
            Worker worker = creatingSteps.get(id).getWorker();
            worker.setName(name);
            creatingSteps.put(id, new WorkAnswer(1, worker));
            return true;
        }
        return false;
    }


    public boolean addWorkerPasswordStep(long id, String password) {
        if (!creatingSteps.containsKey(id)) {
            throw new RuntimeException("Что-то не так с шагом добавления паспорта, вернитесь обратно");
        }
        if (creatingSteps.get(id).getStepNum() == 1) {
            Worker worker = creatingSteps.get(id).getWorker();
            worker.setPassword(password);
            creatingSteps.put(id, new WorkAnswer(2, worker));
            return true;
        }
        return false;
    }

    public boolean addWorkerPositionStep(long id, String position) {
        if (!creatingSteps.containsKey(id)) {
            throw new RuntimeException("Что-то не так с шагом добавления должности, вернитесь обратно");
        }
        if (creatingSteps.get(id).getStepNum() == 2) {
            Worker worker = creatingSteps.get(id).getWorker();
            worker.setPosition(position);
            creatingSteps.put(id, new WorkAnswer(3, worker));
            return true;
        }
        return false;
    }

    public void finishWorkerSteps(long id) {
        if (!creatingSteps.containsKey(id)) {
            throw new RuntimeException("Что-то не так с шагом завершения составления рабочего, вернитесь обратно");
        }
        if (creatingSteps.get(id).getStepNum() == 3) {
            creatingSteps.remove(id);
        }
    }

    public int createWorkerBySteps(long id, int step, String answer) {
        switch (step) {
            case -1:
                startCreateWorkerStep(id);
                return 0;
            case 0:
                addWorkerNameStep(id, answer);
                return 1;
            case 1:
                addWorkerPasswordStep(id, answer);
                return 2;
            case 2:
                addWorkerPositionStep(id, answer);
                return 3;
            case 3:
                create(creatingSteps.get(id).getWorker());
                finishWorkerSteps(id);
                return 4;
            default:
                throw new RuntimeException("Что то не так");
        }
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
