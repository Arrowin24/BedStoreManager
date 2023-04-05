package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;
import ru.arrowin.bedstoremanager.repository.CreatedSmallFurnitureRepository;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SmallFurnitureService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
//Сервис по работе со сделанной малой мебелью
@Service
public class CreatedSmallFurnitureServiceImpl implements CreatedSmallFurnitureService {
    private final CreatedSmallFurnitureRepository furnitureRepository;
    private final SmallFurnitureService smallFurnitureService;

    public CreatedSmallFurnitureServiceImpl(CreatedSmallFurnitureRepository furnitureRepository, SmallFurnitureService smallFurnitureService) {
        this.furnitureRepository = furnitureRepository;
        this.smallFurnitureService = smallFurnitureService;
    }

    //Метод создания новой сделанной малой мебели и добавления ее в базу данных
    @Override
    public void add(CreatedSmallFurniture smallFurniture) {
        furnitureRepository.save(smallFurniture);
    }
    //Метод удаления из базы данных сделнной малой мебели
    @Override
    public void delete(Integer smallFurnitureId){
        furnitureRepository.deleteById(smallFurnitureId);
    }
    //Метод получания списка сделанной малой мебели
    @Override
    public List<CreatedSmallFurniture> readAll() {
        return furnitureRepository.findAll();
    }
    //Вывод списка сделанной малой мебели за конкретный день
    @Override
    public List<String> getTodayCreatedSmallFurniture(Long userId) {
        LocalDate today = LocalDate.now();
        return getSmallFurnitureIdStream(userId, today).map(id ->smallFurnitureService.getSmallFurniture(id).getName()).toList();
    }
    //Вывод стрима с айди и датой за конкретный день
    private Stream<Integer> getSmallFurnitureIdStream(Long userId, LocalDate date) {
        return furnitureRepository.findCreatedSmallFurnitureBy(userId, date).stream().map(CreatedSmallFurniture::getSmallFurnitureId);
    }
    //Получения списка с зарплатой за сделанную малую мебель для конерктного юзера за конкретный день
    @Override
    public Double getTodaySmallFurnitureSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getSmallFurnitureIdStream(userId, today).mapToDouble(id->smallFurnitureService.getSmallFurniture(id).getCost()).sum();
    }
    //Вывод сделанной малой мебели в колличестве в штуках за конкретный день для конкретного пользователя
    @Override
    public double getSmallFurnitureTodayByAmount(Long userid){
        LocalDate today = LocalDate.now();
        return getSmallFurnitureIdStream(userid,today).mapToDouble(id->smallFurnitureService.readAll().size()).count();
    }
    //Вывод зарплаты за месяц за сделанную малую мебель
    @Override
    public double getCurrentMonthSmallFurnitureSalary(Long userId){
        int month = LocalDate.now().getMonth().getValue();
        return furnitureRepository.findSmallFurnitureByMonth(userId,month).stream().mapToDouble(sf->smallFurnitureService.getSmallFurniture(sf.getSmallFurnitureId()).getCost()).sum();
    }
    //Вывод общего колличества сделанной малой мебели за конкретный день на всем производстве
    @Override
    public double getAmountSmallFurnitureForMaster(){
        LocalDate today = LocalDate.now();
        return (long) furnitureRepository.findSmallFurnitureBy(today).size();
    }
    //вывод айди и названий малой мебели списком
    @Override
    public List<String> getSmallFurnitureNameAndId(Long userId){
        LocalDate today = LocalDate.now();
        return furnitureRepository.findCreatedSmallFurnitureBy(userId, today).stream().map(sf->smallFurnitureService.getSmallFurniture(sf.getSmallFurnitureId()).getName()+"&&"+sf.getId()).toList();
    }
}
