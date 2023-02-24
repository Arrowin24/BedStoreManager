package ru.arrowin.bedstoremanager.models.answers;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "small_furniture")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SmallFurniture  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private double cost;

    public SmallFurniture(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallFurniture that = (SmallFurniture) o;
        return id == that.id && Double.compare(that.cost, cost) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cost);
    }
}
