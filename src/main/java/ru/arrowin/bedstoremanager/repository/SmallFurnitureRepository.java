package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;
/***
 * Репозиторий который хранит в себе всю малую мебель на предприятии которую можно изготовить
 */
public interface SmallFurnitureRepository extends JpaRepository<SmallFurniture,Integer> {
}
