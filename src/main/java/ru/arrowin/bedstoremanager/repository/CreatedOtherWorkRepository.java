package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;

import java.time.LocalDate;
import java.util.List;

public interface CreatedOtherWorkRepository extends JpaRepository<CreatedOtherWork, Integer> {

    @Query(value = "SELECT * FROM created_other_work WHERE user_id=?1 and date=?2",
           nativeQuery = true)
    List<CreatedOtherWork> findOtherWorksBy(Long userId, LocalDate date);
}
