package ru.arrowin.bedstoremanager.step;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

public class CreateFurnitureStep extends Step{

    public CreateFurnitureStep(
            StepsContainer container, SendBotMessageService sendBotMessageService)
    {
        super(container, sendBotMessageService);
    }

    @Override
    public void startStep(Update update) {

    }

    @Override
    public void doStep(Update update) {

    }
}
