package ru.arrowin.bedstoremanager.command.delete;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.DeleteKeyBoard;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class DeleteCommand extends Command {
    private final static String TEXT = "Выберете что нужно удалить: ";
    private final SendBotMessageService sendBotMessageService;


    public DeleteCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.DELETE_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(TEXT);
        message.setReplyMarkup(new DeleteKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
