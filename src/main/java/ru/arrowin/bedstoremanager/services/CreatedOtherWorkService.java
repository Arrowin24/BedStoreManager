package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;

import java.util.List;

public interface CreatedOtherWorkService {
    void add(CreatedOtherWork otherWork);



    void delete(Integer otherWorkId);

    List<CreatedOtherWork> readAll();

    List<String> getTodayCreatedOtherWork(Long userId);

    Double getTodayOtherWorkSalary(Long userId);

    double getOtherWorkTodayByAmount(Long userid);

    double getCurrentMonthOtherWorkSalary(Long userId);

    double getAmountOtherWorkForMaster();
}
