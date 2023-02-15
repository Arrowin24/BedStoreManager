package ru.arrowin.bedstoremanager.services;

import ru.arrowin.bedstoremanager.models.answers.SalaryBed;
import ru.arrowin.bedstoremanager.repository.SalaryBedRepository;

import java.util.List;

public interface SalaryBedService {


    void add(SalaryBed salaryBed);

    List<SalaryBed> readAll();

    double allSalaryToDay();
}
