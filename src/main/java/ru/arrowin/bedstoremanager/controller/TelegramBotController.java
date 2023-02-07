package ru.arrowin.bedstoremanager.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.CommandContainer;
import ru.arrowin.bedstoremanager.step.StepsContainer;

@Controller
@Component
@Log4j
public class TelegramBotController extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";
    @Value("${telegram.bot.name}") private String botName;
    @Value("${telegram.bot.token}") private String botToken;

    private final CommandContainer commandContainer;
    private final StepsContainer stepsContainer;


    public TelegramBotController(CommandContainer commandContainer, StepsContainer stepsContainer) {
        this.commandContainer = commandContainer;
        this.stepsContainer = stepsContainer;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = "Какая-то ошибка";
        if (update.hasMessage()) {
            message = update.getMessage().getText();
        }
        if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getData();
        }
        if(stepsContainer.isContains(update)) {
            stepsContainer.getStep(update).doStep(update);
        }
        if (message.startsWith(COMMAND_PREFIX)) {
            commandContainer.retrieveCommand(message).execute(update);
        }
    }

}
