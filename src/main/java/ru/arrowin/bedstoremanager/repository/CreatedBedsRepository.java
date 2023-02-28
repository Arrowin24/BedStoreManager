package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.arrowin.bedstoremanager.models.CreatedBed;

import java.time.LocalDate;
import java.util.List;

public interface CreatedBedsRepository extends JpaRepository<CreatedBed, Integer> {

    @Query(value = "SELECT * FROM created_beds WHERE user_id=?1 and date=?2",
           nativeQuery = true)
    List<CreatedBed> findBedsByWorkerIdAndDate(Long userId, LocalDate date);
}
