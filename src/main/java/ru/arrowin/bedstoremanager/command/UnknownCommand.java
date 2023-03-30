package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;


/*
* Команда, выводящая доступные пользователю команды.
* Вызывается при вводе неизвестной команды пользователем.
* */
@Component
public class UnknownCommand extends Command {
    public static final String UNKNOWN_MESSAGE = "Я не понимаю вас. Пожалуйста выберете пункт из клавиатуры ниже:";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.UNKNOWN);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setText(UNKNOWN_MESSAGE);
        message.setChatId(getId(update));
        sendBotMessageService.sendMessage(message);
    }
}
