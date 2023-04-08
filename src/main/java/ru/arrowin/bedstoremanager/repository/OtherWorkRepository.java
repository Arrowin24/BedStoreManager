package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.answers.OtherWork;

/***
 * Репозиторий со всей иной работой на предприятии которая храниться в базе данных
 */
public interface OtherWorkRepository extends JpaRepository<OtherWork,Integer> {
}
