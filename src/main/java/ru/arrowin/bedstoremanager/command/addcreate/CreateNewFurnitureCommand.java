package ru.arrowin.bedstoremanager.command.addcreate;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;


/**
* Команда по созданию и добавлению новой мебели в базу данных.
* */
@Component
public class CreateNewFurnitureCommand extends Command {
    SendBotMessageService sendBotMessageService;

    public CreateNewFurnitureCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.CREATE_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

    }
}
