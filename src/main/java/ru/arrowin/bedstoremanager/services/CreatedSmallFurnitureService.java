package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;

import java.util.List;

public interface CreatedSmallFurnitureService {
    void add(CreatedSmallFurniture smallFurniture);



    void delete(Integer smallFurnitureId);

    List<CreatedSmallFurniture> readAll();

    List<String> getTodayCreatedSmallFurniture(Long userId);

    Double getTodaySmallFurnitureSalary(Long userId);

    double getSmallFurnitureTodayByAmount(Long userid);

    double getCurrentMonthSmallFurnitureSalary(Long userId);

    double getAmountSmallFurnitureForMaster();



    List<String> getSmallFurnitureNameAndId(Long userId);
}
