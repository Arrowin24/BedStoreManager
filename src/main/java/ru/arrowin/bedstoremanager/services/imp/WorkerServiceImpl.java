package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.repository.WorkerRepository;
import ru.arrowin.bedstoremanager.services.WorkerService;

import java.util.List;
//Сервис для работы с базой данных сотрудников
@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;


    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    //Метод создания нового юзера ит добавления в базу данных
    @Override
    public void create(Worker worker) {
        workerRepository.save(worker);
    }
    //Метод вычитывания юзера из базы данных
    @Override
    public List<Worker> readAll() {
        return workerRepository.findAll();
    }

    //Метод проверки на наличие данного юзера в базе данных
    @Override
    public Worker read(long id) {
        if (workerRepository.findById(id).isPresent()) {
            return workerRepository.findById(id).get();
        }
        throw new RuntimeException("Нет такого рабочего");
    }
    //Метод обновления данных о сотруднике по айди
    @Override
    public boolean update(Worker worker, long id) {
        if (workerRepository.existsById(id)) {
            worker.setId(id);
            workerRepository.save(worker);
            return true;
        }
        return false;
    }
    //метод удаления сотрудника из базы данных если такой сотрудник уже имееться по айди
    @Override
    public boolean delete(long id) {
        if (workerRepository.existsById(id)) {
            workerRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
