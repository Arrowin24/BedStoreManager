package ru.arrowin.bedstoremanager.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "beds")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name") private String name;
    @Column(name = "cost") private double cost;



    public Bed(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bed furniture = (Bed) o;
        return Double.compare(furniture.cost, cost) == 0 && name.equals(furniture.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
    }
}
