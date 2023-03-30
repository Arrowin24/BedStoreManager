package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;

import java.util.List;

//Интерфейс для работы с базой данных всей малой мебели на производстве
public interface SmallFurnitureService {
    //Метод создания и добавления малой мебели в базу данных
    void add(SmallFurniture smallFurniture);

    //Метод для вычитывания малой мебели из базы данных
    List<SmallFurniture> readAll();

    //Метод подсчета малой мебели за конкретный день
    double allCostInDay();

    //Метод вызова малой мебели из базы данных по айди
    SmallFurniture getSmallFurniture(int id);
}
