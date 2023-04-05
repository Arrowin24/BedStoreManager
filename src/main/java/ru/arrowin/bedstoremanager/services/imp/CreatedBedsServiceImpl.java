package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.repository.CreatedBedsRepository;
import ru.arrowin.bedstoremanager.services.BedService;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
//Сервис по работе со сделанными кроватями
@Service
public class CreatedBedsServiceImpl implements CreatedBedsService {

    @Value("${symbol.for.split}") private String SPLIT;
    private final CreatedBedsRepository repository;
    private final BedService bedService;

    public CreatedBedsServiceImpl(CreatedBedsRepository repository, BedService bedService) {
        this.repository = repository;
        this.bedService = bedService;
    }
    //Добавление сделанной кровати в базу данных сделанных кроватей
    @Override
    public void add(CreatedBed bed) {
        repository.save(bed);

    }
    //Метод удаления сделанной кровати из базы данных сделанных кроватей
    @Override
    public void delete(Integer bedId){
        repository.deleteById(bedId);
    }
    //Прочитать все сделанные кровати из базы данных со сделанными кроватями
    @Override
    public List<CreatedBed> readAll() {
        return repository.findAll();
    }
    //Метод вывода всех сделанных кроватей определенным пользователем
    @Override
    public List<String> getTodayCreatedBeds(Long userId) {
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userId, today).map(id -> bedService.getBed(id).getName()).toList();

    }
    //Метод получения стрима айди кроватей для определенного пользолвателя в конкретный день
    private Stream<Integer> getBedsIdStream(Long userId, LocalDate date) {
        return repository.findBedsBy(userId, date).stream().map(CreatedBed::getBedId);
    }

    //Метод получания зарплаты за сделанные кровати в конерктный день
    @Override
    public Double getTodayBedSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userId, today).mapToDouble(id->bedService.getBed(id).getCost()).sum();
    }
    //Метод по подсчету сделанных кроватей по колличеству в штуках
    @Override
    public double getBedsTodayByAmount(Long userid){
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userid,today).mapToDouble(id->bedService.readAll().size()).count();
    }
    //Метод по подсчету зарплаты за конкретный месяц за сделанные кровати
    @Override
    public double getCurrentMonthBedSalary(Long userId){
        int month = LocalDate.now().getMonth().getValue();
        return repository.findBedsByMonth(userId,month).stream().mapToDouble(cb->bedService.getBed(cb.getBedId()).getCost()).sum();
    }
    //Метод по подсчеты всех кроватей сделанных производством
    @Override
    public double getAmountBedsForMaster(){
        LocalDate today = LocalDate.now();
        return (long) repository.findBedsBy(today).size();
    }
    //Метод по выводу списка айди и названий кроватей определенного юзера
    @Override
    public List<String> getBedsNameAndId(Long userId){
       LocalDate today = LocalDate.now();
       return repository.findBedsBy(userId, today).stream().map(cb->bedService.getBed(cb.getBedId()).getName()+"&&"+cb.getId()).toList();
    }
}