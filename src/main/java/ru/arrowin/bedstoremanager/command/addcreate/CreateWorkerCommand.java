package ru.arrowin.bedstoremanager.command.addcreate;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.step.CreateWorkerStep;
/**
* Команда, вызывающая начало регистрации нового работника в боте.
* Вызывается при нажатии на кнопку "Регистрация"
* */
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
