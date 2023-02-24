package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.CreatedBed;
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
    public List<CreatedOtherWork> readAll() {
        return otherWorkRepository.findAll();
    }

    @Override
    public List<String> getTodayCreatedOtherWork(Long userId) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userId, today).map(id -> otherWorkService.getOtherWork(id).getName()).toList();
    }
    private Stream<Integer> getOtherWorkIdStream(Long userId, LocalDate date) {
        return otherWorkRepository.findAll().stream()
                .filter(otherWork -> otherWork.getUserId().equals(userId) && otherWork.getDate().isEqual(date))
                .map(CreatedOtherWork::getOtherWorkId);
    }

    @Override
    public Double getTodayOtherWorkSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getOtherWorkIdStream(userId, today).mapToDouble(id->otherWorkService.getOtherWork(id).getCost()).sum();
    }


}
