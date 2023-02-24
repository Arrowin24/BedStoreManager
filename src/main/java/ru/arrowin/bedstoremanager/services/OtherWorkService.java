package ru.arrowin.bedstoremanager.services;


import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.OtherWork;

import java.util.List;

public interface OtherWorkService {
    void add(OtherWork otherWork);

    List<OtherWork> readAll();

    double allCostInDay();
    OtherWork getOtherWork(int id);
}
