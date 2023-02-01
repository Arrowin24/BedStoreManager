package ru.arrowin.bedstoremanager.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.services.WorkerService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j
public class TelegramBotController extends TelegramLongPollingBot {
    @Value("${telegram.bot.name}") private String botName;
    @Value("${telegram.bot.token}") private String botToken;

    private WorkerService workerService;

    public TelegramBotController(WorkerService workerService) {
        this.workerService = workerService;
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
        if (update.hasMessage()) {
            var originalMessage = update.getMessage();
            log.debug(originalMessage.getText());
            log.debug(originalMessage.getFrom().toString());
            if (update.getMessage().hasText()) {
                if (originalMessage.getText().contains("add")) {
                    Long id = originalMessage.getFrom().getId();
                    String name = originalMessage.getFrom().getUserName() + " " + originalMessage.getFrom().getFirstName();
                    workerService.create(new Worker(id, name, "123", "Работник"));
                    sendMessage(
                            new SendMessage(originalMessage.getChatId().toString(), workerService.read(id).toString()));
                }
                if (originalMessage.getText().toLowerCase().contains("клавиат")) {
                    sendInlineKeyBoardMessage(originalMessage.getChatId());
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery query = update.getCallbackQuery();
            keyBoardHandling(query);
        }
    }

    public void sendInlineKeyBoardMessage(long chanId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Добавление нового сотрудника");
        button1.setCallbackData("add");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Вывод всех сотрудников");
        button2.setCallbackData("allWorker");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(button1);
        keyboardButtonsRow2.add(button2);
        List<List<InlineKeyboardButton>> buttonsColons = new ArrayList<>();
        buttonsColons.add(keyboardButtonsRow1);
        buttonsColons.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(buttonsColons);
        SendMessage message = new SendMessage();
        message.setChatId(chanId);
        message.setText("Выберете пункт");
        message.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

    public void keyBoardHandling(CallbackQuery query) {
        String answer ="";
        switch (query.getData()) {
            case "allWorker":
                answer = workerService.readAll().toString();
                break;
            case "add":
                Long id = query.getFrom().getId();
                String name = query.getFrom().getUserName() + " " + query.getFrom().getFirstName();
                workerService.create(new Worker(id, name, "123", "Работник"));
                answer = workerService.read(query.getFrom().getId()).toString();
                break;
            default:
                answer = "Что-то пошло не так";
                log.debug(query.getData());
        }
        try {
            execute(new SendMessage(query.getFrom().getId().toString(), answer));

        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

    public void sendMessage(SendMessage message) {
        if (message == null) {
            return;
        }
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

}
