package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.Bed;

import java.util.List;
//Интерфейс по работе с базой данных кроватей
public interface BedService {
    //Создание кровати и добавление в базу данных
    void add(Bed bed);
    //Получение кровати из базы данных
    Bed getBed(int id);
    //вычитывание кровати из базы данных в виде списка
    List<Bed> readAll();
    //Подсчет кроватей выполненных за конкретный день
    double allCostInDay();
}
