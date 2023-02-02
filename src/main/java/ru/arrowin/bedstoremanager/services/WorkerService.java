package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.Worker;

import java.util.List;

public interface WorkerService {
    int getStep(long id);

    boolean isCreating(long id);

    String createWorkerBySteps(long id, int step, String answer);

    void create(Worker worker);

    List<Worker> readAll();

    Worker read(long id);

    boolean update(Worker worker, long id);

    boolean delete(long id);
}
