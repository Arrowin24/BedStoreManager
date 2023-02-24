package ru.arrowin.bedstoremanager.models.answers;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "other_work")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OtherWork  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private double cost;

    public OtherWork(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherWork otherWork = (OtherWork) o;
        return id == otherWork.id && Double.compare(otherWork.cost, cost) == 0 && Objects.equals(name, otherWork.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost);
    }
}