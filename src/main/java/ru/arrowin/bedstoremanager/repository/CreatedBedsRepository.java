package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.arrowin.bedstoremanager.models.CreatedBed;

import java.time.LocalDate;
import java.util.List;

public interface CreatedBedsRepository extends JpaRepository<CreatedBed, Integer> {

    @Query(value = "SELECT * FROM created_beds WHERE user_id=?1 and date=?2",
           nativeQuery = true)
    List<CreatedBed> findBedsBy(Long userId, LocalDate date);

    @Query(value = "SELECT * FROM created_beds WHERE user_id=?1 AND ?2=(SELECT EXTRACT(MONTH FROM date ))", nativeQuery = true)
    List<CreatedBed> findBedsByMonth(Long userId, int monthNum);
}
