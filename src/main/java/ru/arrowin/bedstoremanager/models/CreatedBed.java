package ru.arrowin.bedstoremanager.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
//Класс по реализации обьектов сделанных кроватей на производстве

@Entity
@Table(name = "created_beds")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreatedBed {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bed_id")
    private int bedId;

    @Column(name = "date")
    private LocalDate date;

    public CreatedBed(Long userId, int bedId, LocalDate date) {
        this.userId = userId;
        this.bedId = bedId;
        this.date = date;
    }
}
