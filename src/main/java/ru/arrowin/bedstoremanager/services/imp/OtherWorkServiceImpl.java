package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.answers.OtherWork;
import ru.arrowin.bedstoremanager.models.answers.SalaryBed;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;
import ru.arrowin.bedstoremanager.repository.OtherWorkRepository;
import ru.arrowin.bedstoremanager.services.OtherWorkService;

import java.util.List;
@Service
public class OtherWorkServiceImpl implements OtherWorkService {
    private final OtherWorkRepository otherWorkRepository;

    public OtherWorkServiceImpl(OtherWorkRepository otherWorkRepository) {
        this.otherWorkRepository = otherWorkRepository;
    }

    @Override
    public void add(OtherWork otherWork) {
        otherWorkRepository.save(otherWork);
    }

    @Override
    public List<OtherWork> readAll() {
      return otherWorkRepository.findAll();
    }

    @Override
    public double allCostInDay() {
        return  otherWorkRepository.findAll().stream().mapToDouble(OtherWork::getCost).sum();
    }

    @Override
    public OtherWork getOtherWork(int id) {
        return otherWorkRepository.findById(id).get();
    }


}
