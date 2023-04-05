package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;

import java.util.List;
//Интерфейс по работе с малой мебелью сделанной на производстве
public interface CreatedSmallFurnitureService {
    //Метод создания новой сделанной малой мебели и добавления ее в базу данных
    void add(CreatedSmallFurniture smallFurniture);
    //Метод удаления из базы данных сделнной малой мебели
    void delete(Integer smallFurnitureId);
    //Метод получания списка сделанной малой мебели
    List<CreatedSmallFurniture> readAll();
    //Вывод списка сделанной малой мебели за конкретный день
    List<String> getTodayCreatedSmallFurniture(Long userId);
    //Получения списка с зарплатой за сделанную малую мебель для конерктного юзера за конкретный день
    Double getTodaySmallFurnitureSalary(Long userId);
    //Вывод сделанной малой мебели в колличестве в штуках за конкретный день для конкретного пользователя
    double getSmallFurnitureTodayByAmount(Long userid);
    //Вывод зарплаты за месяц за сделанную малую мебель
    double getCurrentMonthSmallFurnitureSalary(Long userId);
    //Вывод общего колличества сделанной малой мебели за конкретный день на всем производстве
    double getAmountSmallFurnitureForMaster();
    //вывод айди и названий малой мебели списком
    List<String> getSmallFurnitureNameAndId(Long userId);
}
