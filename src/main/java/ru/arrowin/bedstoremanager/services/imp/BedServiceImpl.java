package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.SalaryBed;
import ru.arrowin.bedstoremanager.repository.BedRepository;
import ru.arrowin.bedstoremanager.services.BedService;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {
    private final BedRepository bedRepository;


    public BedServiceImpl(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Override
    public void add(Bed bed) {
        bedRepository.save(bed);
    }

    @Override
    public List<Bed> readAll() {
        return bedRepository.findAll();
    }
    @Override
    public double allCostInDay(){
         return  bedRepository.findAll().stream().mapToDouble(Bed::getCost).sum();
    }




}
