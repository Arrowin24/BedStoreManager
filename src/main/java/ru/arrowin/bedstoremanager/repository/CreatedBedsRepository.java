package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.CreatedBed;

public interface CreatedBedsRepository extends JpaRepository<CreatedBed, Integer> {
}
