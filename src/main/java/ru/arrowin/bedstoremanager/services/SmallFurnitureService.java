package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;

import java.util.List;

public interface SmallFurnitureService {
    void add(SmallFurniture smallFurniture);

    List<SmallFurniture> readAll();

    double allCostInDay();
   SmallFurniture getSmallFurniture(int id);
}
