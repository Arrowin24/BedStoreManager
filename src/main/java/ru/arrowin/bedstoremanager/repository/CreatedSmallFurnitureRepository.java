package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;

import java.time.LocalDate;
import java.util.List;
//Репозиторий со всей сделанной малой мебелью находящейся в базе данных
public interface CreatedSmallFurnitureRepository extends JpaRepository<CreatedSmallFurniture, Integer> {

    @Query(value = "SELECT * FROM created_small_furniture WHERE user_id=?1 and date=?2",
           nativeQuery = true)
    List<CreatedSmallFurniture> findCreatedSmallFurnitureBy(Long userId, LocalDate date);
    @Query(value = "SELECT * FROM  created_small_furniture  WHERE date=?1" ,nativeQuery = true)
    List<CreatedSmallFurniture> findSmallFurnitureBy(LocalDate date);

    @Query(value = "SELECT * FROM created_small_furniture WHERE user_id=?1 AND ?2=(SELECT EXTRACT(MONTH FROM date ))", nativeQuery = true)
    List<CreatedSmallFurniture> findSmallFurnitureByMonth(Long userId, int monthNum);
}
