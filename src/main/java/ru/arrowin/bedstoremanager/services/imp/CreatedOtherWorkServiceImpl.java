package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;
import ru.arrowin.bedstoremanager.repository.CreatedOtherWorkRepository;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.OtherWorkService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CreatedOtherWorkServiceImpl implements CreatedOtherWorkService {
    private final CreatedOtherWorkRepository otherWorkRepository;
    private final OtherWorkService otherWorkService;

    public CreatedOtherWorkServiceImpl(CreatedOtherWorkRepository otherWorkRepository, OtherWorkService otherWorkService) {
        this.otherWorkRepository = otherWorkRepository;
        this.otherWorkService = otherWorkService;
    }


    @Override
    public void add(CreatedOtherWork otherWork) {
        otherWorkRepository.save(otherWork);
    }
    @Override
    public void delete(Integer otherWorkId){
        otherWorkRepository.deleteById(otherWorkId);
    }

    @Override
    public List<CreatedOtherWork> readAll() {
        return otherWorkRepository.findAll();
    }

    @Override
    public List<String> getTodayCreatedOtherWork(Long userId) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userId, today).map(id -> otherWorkService.getOtherWork(id).getName()).toList();
    }

    private Stream<Integer> getOtherWorkIdStream(Long userId, LocalDate date) {
        return otherWorkRepository.findOtherWorksBy(userId, date).stream().map(CreatedOtherWork::getOtherWorkId);
    }

    @Override
    public Double getTodayOtherWorkSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userId, today).mapToDouble(id -> otherWorkService.getOtherWork(id).getCost()).sum();
    }

    @Override
    public double getOtherWorkTodayByAmount(Long userid) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userid, today).mapToDouble(id -> otherWorkService.readAll().size()).count();
    }

    @Override
    public double getCurrentMonthOtherWorkSalary(Long userId) {
        int month = LocalDate.now().getMonth().getValue();
        return otherWorkRepository.findOtherWorkByMonth(userId, month).stream().mapToDouble(ow -> otherWorkService.getOtherWork(ow.getOtherWorkId()).getCost()).sum();
    }

    @Override
    public double getAmountOtherWorkForMaster() {
        LocalDate today = LocalDate.now();
        return (long) otherWorkRepository.findOtherWorkBy(today).size();
    }
    @Override
    public List<String> getOtherWorkNameAndId(Long userId){
        LocalDate today = LocalDate.now();
        return otherWorkRepository.findOtherWorksBy(userId, today).stream().map(ow->otherWorkService.getOtherWork(ow.getOtherWorkId()).getName()+"&&"+ow.getId()).toList();
    }

}
