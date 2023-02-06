package ru.arrowin.bedstoremanager.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "workers")
@Getter
@Setter
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name") private String name;
    @Column(name = "password") private String password;
    @Column(name = "position") private String position;

    @Override
    public String toString() {
        return "Worker: " + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", position='" + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Worker worker = (Worker) o;
        return id != null && Objects.equals(id, worker.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
