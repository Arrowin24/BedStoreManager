package ru.arrowin.bedstoremanager.services.imp;
//Сервис по работе с базой данных всей малой мебели на производстве
import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;
import ru.arrowin.bedstoremanager.repository.SmallFurnitureRepository;
import ru.arrowin.bedstoremanager.services.SmallFurnitureService;

import java.util.List;
@Service
public class SmallFurnitureServiceImpl implements SmallFurnitureService {
    private final SmallFurnitureRepository smallFurnitureRepository;

    public SmallFurnitureServiceImpl(SmallFurnitureRepository smallFurnitureRepository) {
        this.smallFurnitureRepository = smallFurnitureRepository;
    }

    //Метод создания и добавления малой мебели в базу данных
    @Override
    public void add(SmallFurniture smallFurniture) {
       smallFurnitureRepository.save(smallFurniture);
    }
    //Метод для вычитывания малой мебели из базы данных
    @Override
    public List<SmallFurniture> readAll() {
        return smallFurnitureRepository.findAll();
    }
    //Метод подсчета малой мебели за конкретный день
    @Override
    public double allCostInDay() {
        return  smallFurnitureRepository.findAll().stream().mapToDouble(SmallFurniture::getCost).sum();
    }
    //Метод вызова малой мебели из базы данных по айди
    @Override
    public SmallFurniture getSmallFurniture(int id) {
        return   smallFurnitureRepository.findById(id).get();
    }
}
