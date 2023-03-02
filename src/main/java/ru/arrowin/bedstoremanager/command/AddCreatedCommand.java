package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.CreatedMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class AddCreatedCommand extends Command{

    private final static String TEXT = "Выберите тип изготовленной работы: ";
    private final SendBotMessageService sendBotMessageService;

    public AddCreatedCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.ADD_CREATED);
        this.sendBotMessageService =sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(TEXT);
        message.setReplyMarkup(new CreatedMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
