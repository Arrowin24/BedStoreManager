package ru.arrowin.bedstoremanager.services.imp;

import org.springframework.stereotype.Service;
import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.repository.CreatedBedsRepository;
import ru.arrowin.bedstoremanager.services.BedService;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CreatedBedsServiceImpl implements CreatedBedsService {

    private final CreatedBedsRepository repository;
    private final BedService bedService;

    public CreatedBedsServiceImpl(CreatedBedsRepository repository, BedService bedService) {
        this.repository = repository;
        this.bedService = bedService;
    }

    @Override
    public void add(CreatedBed bed) {
        repository.save(bed);
    }

    @Override
    public List<CreatedBed> readAll() {
        return repository.findAll();
    }

    @Override
    public List<String> getTodayCreatedBeds(Long userId) {
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userId, today).map(id -> bedService.getBed(id).getName()).toList();

    }

    private Stream<Integer> getBedsIdStream(Long userId, LocalDate date) {
        return repository.findBedsByWorkerIdAndDate(userId,date).stream().map(CreatedBed::getBedId);
    }

    @Override
    public Double getTodayBedSalary(Long userId) {
        LocalDate today = LocalDate.now();
        return getBedsIdStream(userId, today).mapToDouble(id->bedService.getBed(id).getCost()).sum();
    }
}