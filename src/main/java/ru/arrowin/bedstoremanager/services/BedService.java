package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.Bed;

import java.util.List;

public interface BedService {
    void add(Bed bed);

    Bed getBed(int id);

    List<Bed> readAll();

    double allCostInDay();
}
