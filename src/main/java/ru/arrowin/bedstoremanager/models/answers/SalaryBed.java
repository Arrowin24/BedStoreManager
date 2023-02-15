package ru.arrowin.bedstoremanager.models.answers;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "salary_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SalaryBed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userid")
    private int userId;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private double cost;

    public SalaryBed(int userId, String name, double cost) {
        this.userId = userId;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryBed salaryBed = (SalaryBed) o;
        return userId == salaryBed.userId && Double.compare(salaryBed.cost, cost) == 0 && Objects.equals(name, salaryBed.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, cost);
    }
}
