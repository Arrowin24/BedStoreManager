package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.Furniture;

public interface FurnitureRepository extends JpaRepository<Furniture,Double>{
}
