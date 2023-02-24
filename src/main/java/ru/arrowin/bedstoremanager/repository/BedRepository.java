package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.Bed;

public interface BedRepository extends JpaRepository<Bed,Integer>{
}
