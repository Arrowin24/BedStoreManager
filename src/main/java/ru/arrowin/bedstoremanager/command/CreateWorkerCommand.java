package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.step.CreateWorkerStep;

@Component
public class CreateWorkerCommand extends Command {
    private final CreateWorkerStep createWorkerStep;

    public CreateWorkerCommand(CreateWorkerStep workerStep) {
        super(CommandName.CREATE_WORKER);
        createWorkerStep = workerStep;
    }

    @Override
    public void execute(Update update) {
        createWorkerStep.startStep(update);
    }
}
