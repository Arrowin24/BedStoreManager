package ru.arrowin.bedstoremanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "workers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {
    @Id
    @Column(name = "id")
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name") private String name;
    @Column(name = "password") private String password;
    @Column(name = "position") private String position;

    @Override
    public String toString() {
        return "Worker: " + "id=" + id + ", name='" + name + '\'' + ", password='" + password + '\'' + ", position='" + position;
    }
}
