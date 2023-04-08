package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.Bed;
/***
 * Репозиторий для работы со всеми кроватями на предприятии , находящимися в базе данных
 */
public interface BedRepository extends JpaRepository<Bed,Integer>{
}
