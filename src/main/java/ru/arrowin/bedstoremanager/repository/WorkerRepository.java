package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
