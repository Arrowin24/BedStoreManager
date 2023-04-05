package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.repository.BedRepository;
import ru.arrowin.bedstoremanager.services.BedService;

import java.util.List;

//Класс по работе с методами создания и сохранения кроватей в базу данных
@Service
public class BedServiceImpl implements BedService {
    private final BedRepository bedRepository;


    public BedServiceImpl(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    //Метод добавления кровати в базу данных
    @Override
    public void add(Bed bed) {
        bedRepository.save(bed);
    }

    //Метод получания кровати по ее айди номеру
    @Override
    public Bed getBed(int id) {
        return bedRepository.findById(id).get();
    }
    //Метод вычитывания всех кроватей из базы данных
    @Override
    public List<Bed> readAll() {
        return bedRepository.findAll();
    }
    //Метод по подсчеты зарплаты за сделанные кровати на текущий день
    @Override
    public double allCostInDay() {
        return bedRepository.findAll().stream().mapToDouble(Bed::getCost).sum();
    }


}
