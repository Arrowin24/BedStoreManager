package ru.arrowin.bedstoremanager.models.answers;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "created_small_furniture")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreatedSmallFurniture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "small_furniture_id")
    private int smallFurnitureId;

    @Column(name = "date")
    private LocalDate date;

    public CreatedSmallFurniture(Long userId, int smallFurnitureId, LocalDate date) {
        this.userId = userId;
        this.smallFurnitureId = smallFurnitureId;
        this.date = date;
    }
}
