package ru.arrowin.bedstoremanager.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.StartKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.WorkerKeyBoard;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    private final static String HELP_MESSAGE = "Я сейчас выведу клавиатуру со всеми возможными командами, которые доступны тебе";

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(HELP_MESSAGE);
        message.setReplyMarkup(new WorkerKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
