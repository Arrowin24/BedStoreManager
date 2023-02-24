package ru.arrowin.bedstoremanager.models.answers;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "created_other_work")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreatedOtherWork {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "other_work_id")
    private int otherWorkId;

    @Column(name = "date")
    private LocalDate date;

    public CreatedOtherWork(Long userId, int otherWorkId, LocalDate date) {
        this.userId = userId;
        this.otherWorkId = otherWorkId;
        this.date = date;
    }
}
