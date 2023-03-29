package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.CreatedBed;

import java.util.List;

public interface CreatedBedsService {
    void add(CreatedBed bed);



    void delete(Integer bedId);

    List<CreatedBed> readAll();

    List<String> getTodayCreatedBeds(Long userId);

    Double getTodayBedSalary(Long userId);

    double getBedsTodayByAmount(Long userid);


    double getCurrentMonthBedSalary(Long userId);

    double getAmountBedsForMaster();
}
