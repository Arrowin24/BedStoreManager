package ru.arrowin.bedstoremanager.services.imp;
//Сервис для работы с базой данных всей иной работы
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
    //Метод добавления новой иной работы в базу данных
    @Override
    public void add(OtherWork otherWork) {
        otherWorkRepository.save(otherWork);
    }
    //Метод вычитывания из базы данных иной работы
    @Override
    public List<OtherWork> readAll() {
      return otherWorkRepository.findAll();
    }
    //Метод для подсчеты суммы иных работ за конкретный день
    @Override
    public double allCostInDay() {
        return  otherWorkRepository.findAll().stream().mapToDouble(OtherWork::getCost).sum();
    }
    //Метод получения иной работы по айди
    @Override
    public OtherWork getOtherWork(int id) {
        return otherWorkRepository.findById(id).get();
    }


}
