package ru.arrowin.bedstoremanager.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

public class UnknownCommand implements Command {
    public static final String UNKNOWN_MESSAGE = "Я не понимаю вас. Пожалуйста выберете пункт из клавиатуры ниже:";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

    }
}
