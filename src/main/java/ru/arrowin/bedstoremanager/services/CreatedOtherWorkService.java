package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;

import java.util.List;
//Интерфейс для работы с иными работами сделанными на производстве
public interface CreatedOtherWorkService {
    //Метод создания сделанной иной работы конкретным пользователем
    void add(CreatedOtherWork otherWork);


    //Метод по удалению иной работы для конкретного пользователя
    void delete(Integer otherWorkId);
    //Метод получения всей иной работы из базы данных для конкретного пользователя
    List<CreatedOtherWork> readAll();
    //Вывод списка всей сделанной иной работы для конкретного пользователя
    List<String> getTodayCreatedOtherWork(Long userId);
    //Подсчет зарплаты за сделанные иные работы для конкретного пользователя за конкретный день
    Double getTodayOtherWorkSalary(Long userId);
    //Подсчет сделанных иных работ в штуках для конкретного пользователя в конерктный день
    double getOtherWorkTodayByAmount(Long userid);
    //Подсчет сделанных иных работ в штуках для конкретного пользователя в конкретный месяц
    double getCurrentMonthOtherWorkSalary(Long userId);
    //Подсчет сделанных иных работ в штуках для всего предприятия за конкретный день
    double getAmountOtherWorkForMaster();
    //Метод по выводу списка айди и названий иных работ определенного юзера
    List<String> getOtherWorkNameAndId(Long userId);
}
