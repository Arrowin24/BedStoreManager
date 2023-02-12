package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.Furniture;
import ru.arrowin.bedstoremanager.repository.FurnitureRepository;
import ru.arrowin.bedstoremanager.services.FurnitureService;

import java.util.List;

@Service
public class FurnitureServiceImpl implements FurnitureService {
    private final FurnitureRepository furnitureRepository;


    public FurnitureServiceImpl(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    @Override
    public void add(Furniture furniture) {
        furnitureRepository.save(furniture);
    }

    @Override
    public List<Furniture> readAll() {
        return furnitureRepository.findAll();
    }
    @Override
    public double allCostInDay(){
         return  furnitureRepository.findAll().stream().mapToDouble(Furniture::getCost).sum();
    }

  /*  public double getAllSpend(){
       return List<Furniture> furnitures = furnitureRepository.findAll().stream().mapToDouble(Furniture::getCost).sum();
    }
*/
}
