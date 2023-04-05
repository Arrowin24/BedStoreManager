package ru.arrowin.bedstoremanager.services;
import ru.arrowin.bedstoremanager.models.Worker;

import java.util.List;
//Интерфейс для работы с базой данных сотрудников

public interface WorkerService {
    //Метод создания нового юзера ит добавления в базу данных
    void create(Worker worker);
    //Метод вычитывания юзера из базы данных
    List<Worker> readAll();
    //Метод проверки на наличие данного юзера в базе данных
    Worker read(long id);
    //Метод обновления данных о сотруднике по айди
    boolean update(Worker worker, long id);
    //метод удаления сотрудника из базы данных если такой сотрудник уже имееться по айди
    boolean delete(long id);
}
