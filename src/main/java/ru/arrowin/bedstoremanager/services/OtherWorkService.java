package ru.arrowin.bedstoremanager.services;

//Интерфейс по работе с базой данных всех иных работ на производстве
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.OtherWork;

import java.util.List;

public interface OtherWorkService {
    //Метод добавления новой иной работы в базу данных
    void add(OtherWork otherWork);
    //Метод вычитывания из базы данных иной работы
    List<OtherWork> readAll();
    //Метод для подсчеты суммы иных работ за конкретный день
    double allCostInDay();
    //Метод получения иной работы по айди
    OtherWork getOtherWork(int id);
}
