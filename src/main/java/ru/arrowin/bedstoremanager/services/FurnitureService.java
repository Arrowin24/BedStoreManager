package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.Furniture;

import java.util.List;

public interface FurnitureService {
    void add(Furniture furniture);

    List<Furniture> readAll();
}
