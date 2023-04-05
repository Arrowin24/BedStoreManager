package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.CreatedBed;

import java.util.List;
//Интерфейс по работе со сделанными кроватями
public interface CreatedBedsService {
    //Создание сделанной кровати и добавление в базу данных сделанных кроватей
    void add(CreatedBed bed);


    //Удаления кровати из базы даннхы сделанных кроватей
    void delete(Integer bedId);
    //Вычитывание списка кроватей из базы данных сделанных кроватей
    List<CreatedBed> readAll();
    //Получение сделанных кроватей за конкретный день
    List<String> getTodayCreatedBeds(Long userId);
    //Получение зарплаты за сделанные кровати за конкретный день
    Double getTodayBedSalary(Long userId);
    //Получение кол-ва сделанных кроватей в штуках одим юзером
    double getBedsTodayByAmount(Long userid);

    //Получение сделки за сделанные за конкретный месяц кровати
    double getCurrentMonthBedSalary(Long userId);
    //Получения общего кол-ва кроватей за день всем производством
    double getAmountBedsForMaster();
    //Получение списка кроватей по айди
    List<String> getBedsNameAndId(Long userId);
}
