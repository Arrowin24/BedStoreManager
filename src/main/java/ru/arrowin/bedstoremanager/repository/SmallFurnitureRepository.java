package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;

public interface SmallFurnitureRepository extends JpaRepository<SmallFurniture,Integer> {
}
