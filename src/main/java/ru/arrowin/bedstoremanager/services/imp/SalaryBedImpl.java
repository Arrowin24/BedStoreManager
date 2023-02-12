package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.answers.SalaryBed;
import ru.arrowin.bedstoremanager.repository.SalaryBedRepository;
import ru.arrowin.bedstoremanager.services.SalaryBedService;

import java.util.List;

@Service
public  class SalaryBedImpl implements SalaryBedService {

    private final SalaryBedRepository salaryBedRepository;


    public SalaryBedImpl(SalaryBedRepository salaryBedRepository) {
        this.salaryBedRepository = salaryBedRepository;

    }

    @Override
    public void add(SalaryBed salaryBed) {
       salaryBedRepository.save(salaryBed);
    }

    @Override
    public List<SalaryBed> readAll() {
        return salaryBedRepository.findAll();
    }
    @Override
    public double allSalaryToDay(){
        return salaryBedRepository.findAll().stream().mapToDouble(SalaryBed::getCost).sum();
    }


}
