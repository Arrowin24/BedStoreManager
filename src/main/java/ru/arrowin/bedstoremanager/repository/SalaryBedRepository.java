package ru.arrowin.bedstoremanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arrowin.bedstoremanager.models.answers.SalaryBed;

import javax.persistence.criteria.CriteriaBuilder;
/***
 * Репозиторий где храниться вся зарплата за сделнные изделия
 */
public interface SalaryBedRepository extends JpaRepository<SalaryBed, Integer> {
}
