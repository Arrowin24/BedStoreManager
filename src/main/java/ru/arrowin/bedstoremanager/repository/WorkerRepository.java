package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.Worker;

/***
 * Репозиторий со всеми сотрудниками производства
 */
public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
