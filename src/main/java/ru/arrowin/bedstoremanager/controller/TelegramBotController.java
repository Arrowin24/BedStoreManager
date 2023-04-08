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


    /***
     * Знак для определения командных сообщений
     */
    public static String COMMAND_PREFIX = "/";

    /***
     * Разделительный знак для переноса данных между командами
     */
    @Value("${symbol.for.split}") private String SPLIT;

    /***
     * Названия бота
     */
    @Value("${telegram.bot.name}") private String botName;

    /***
     * Токен бота
     */
    @Value("${telegram.bot.token}") private String botToken;

    private final CommandContainer commandContainer;
    private final StepsContainer stepsContainer;

    /***
     *
     * @param commandContainer контейнер для команд
     * @param stepsContainer контейнер для сложных многошаговых команд
     */
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

    /***
     * Метод telegramBot, который постоянно просматривает чат на новые сообщения от пользователя и определяет
     * есть ли новое сообщение или передача данных.
     * @param update данные пришедшие от пользователя на сервис
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = "Какая-то ошибка";
        if (update.hasMessage()) {          // поиск наличия сообщений
            message = update.getMessage().getText();
        }
        if (update.hasCallbackQuery()) {        // если есть клавиатура или данные с нее
            message = update.getCallbackQuery().getData();
        }
        if(stepsContainer.isContains(update)) {     // если это продолжение многостадийной команды
            stepsContainer.getStep(update).doStep(update);
        }
        if (message.startsWith(COMMAND_PREFIX)) {   // если это обычная команда
            String command = message.split(SPLIT)[0];
            commandContainer.retrieveCommand(command).execute(update);
        }
    }
}
