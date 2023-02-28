package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class CreateNewFurnitureCommand extends Command{
    SendBotMessageService sendBotMessageService;

    public CreateNewFurnitureCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.CREATE_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

    }
}
