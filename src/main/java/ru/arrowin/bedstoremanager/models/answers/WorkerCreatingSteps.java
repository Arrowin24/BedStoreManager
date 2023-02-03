package ru.arrowin.bedstoremanager.models.answers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.arrowin.bedstoremanager.models.Worker;

@AllArgsConstructor
@Getter
@Setter
public class WorkerCreatingSteps {
    private int stepNum;
    private Worker worker;

}
