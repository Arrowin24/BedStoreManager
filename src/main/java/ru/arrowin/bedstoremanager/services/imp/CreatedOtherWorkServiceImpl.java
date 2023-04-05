package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;
import ru.arrowin.bedstoremanager.repository.CreatedOtherWorkRepository;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.OtherWorkService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
//Сервис по работе с иной работой на производстве
@Service
public class CreatedOtherWorkServiceImpl implements CreatedOtherWorkService {
    private final CreatedOtherWorkRepository otherWorkRepository;
    private final OtherWorkService otherWorkService;

    public CreatedOtherWorkServiceImpl(CreatedOtherWorkRepository otherWorkRepository, OtherWorkService otherWorkService) {
        this.otherWorkRepository = otherWorkRepository;
        this.otherWorkService = otherWorkService;
    }

    //Метод создания сделанной иной работы конкретным пользователем
    @Override
    public void add(CreatedOtherWork otherWork) {
        otherWorkRepository.save(otherWork);
    }
    //Метод по удалению иной работы для конкретного пользователя
    @Override
    public void delete(Integer otherWorkId){
        otherWorkRepository.deleteById(otherWorkId);
    }
    //Метод получения всей иной работы из базы данных для конкретного пользователя
    @Override
    public List<CreatedOtherWork> readAll() {
        return otherWorkRepository.findAll();
    }
    //Вывод списка всей сделанной иной работы для конкретного пользователя
    @Override
    public List<String> getTodayCreatedOtherWork(Long userId) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userId, today).map(id -> otherWorkService.getOtherWork(id).getName()).toList();
    }
    //Вывод стрима с айди и текущей датой для иных работ
    private Stream<Integer> getOtherWorkIdStream(Long userId, LocalDate date) {
        return otherWorkRepository.findOtherWorksBy(userId, date).stream().map(CreatedOtherWork::getOtherWorkId);
    }
    //Подсчет зарплаты за сделанные иные работы для конкретного пользователя
    @Override
    public Double getTodayOtherWorkSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userId, today).mapToDouble(id -> otherWorkService.getOtherWork(id).getCost()).sum();
    }
    //Подсчет сделанных иных работ в штуках для конкретного пользователя в конерктный день
    @Override
    public double getOtherWorkTodayByAmount(Long userid) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userid, today).mapToDouble(id -> otherWorkService.readAll().size()).count();
    }
    //Подсчет сделанных иных работ в штуках для конкретного пользователя в конерктный месяц
    @Override
    public double getCurrentMonthOtherWorkSalary(Long userId) {
        int month = LocalDate.now().getMonth().getValue();
        return otherWorkRepository.findOtherWorkByMonth(userId, month).stream().mapToDouble(ow -> otherWorkService.getOtherWork(ow.getOtherWorkId()).getCost()).sum();
    }
    //Подсчет сделанных иных работ в штуках для всего предприятия за конкретный день
    @Override
    public double getAmountOtherWorkForMaster() {
        LocalDate today = LocalDate.now();
        return (long) otherWorkRepository.findOtherWorkBy(today).size();
    }
    //Метод по выводу списка айди и названий иных работ определенного юзера
    @Override
    public List<String> getOtherWorkNameAndId(Long userId){
        LocalDate today = LocalDate.now();
        return otherWorkRepository.findOtherWorksBy(userId, today).stream().map(ow->otherWorkService.getOtherWork(ow.getOtherWorkId()).getName()+"&&"+ow.getId()).toList();
    }

}
