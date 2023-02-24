package ru.arrowin.bedstoremanager.services.imp;

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


    @Override
    public void add(SmallFurniture smallFurniture) {
       smallFurnitureRepository.save(smallFurniture);
    }

    @Override
    public List<SmallFurniture> readAll() {
        return smallFurnitureRepository.findAll();
    }

    @Override
    public double allCostInDay() {
        return  smallFurnitureRepository.findAll().stream().mapToDouble(SmallFurniture::getCost).sum();
    }
}
