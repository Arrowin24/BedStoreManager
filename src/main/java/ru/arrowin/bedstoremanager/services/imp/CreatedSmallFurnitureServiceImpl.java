package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;
import ru.arrowin.bedstoremanager.repository.CreatedSmallFurnitureRepository;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SmallFurnitureService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CreatedSmallFurnitureServiceImpl implements CreatedSmallFurnitureService {
    private final CreatedSmallFurnitureRepository furnitureRepository;
    private final SmallFurnitureService smallFurnitureService;

    public CreatedSmallFurnitureServiceImpl(CreatedSmallFurnitureRepository furnitureRepository, SmallFurnitureService smallFurnitureService) {
        this.furnitureRepository = furnitureRepository;
        this.smallFurnitureService = smallFurnitureService;
    }


    @Override
    public void add(CreatedSmallFurniture smallFurniture) {
        furnitureRepository.save(smallFurniture);
    }

    @Override
    public List<CreatedSmallFurniture> readAll() {
        return furnitureRepository.findAll();
    }

    @Override
    public List<String> getTodayCreatedSmallFurniture(Long userId) {
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userId, today).map(id ->smallFurnitureService.getSmallFurniture(id).getName()).toList();
    }
    private Stream<Integer> getBedsIdStream(Long userId, LocalDate date) {
        return furnitureRepository.findCreatedSmallFurnitureBy(userId, date).stream().map(CreatedSmallFurniture::getSmallFurnitureId);
    }

    @Override
    public Double getTodaySmallFurnitureSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userId, today).mapToDouble(id->smallFurnitureService.getSmallFurniture(id).getCost()).sum();
    }
}
