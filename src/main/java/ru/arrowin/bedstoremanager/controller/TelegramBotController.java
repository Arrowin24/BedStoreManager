package ru.arrowin.bedstoremanager.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.arrowin.bedstoremanager.command.CommandContainer;
import ru.arrowin.bedstoremanager.models.Furniture;
import ru.arrowin.bedstoremanager.services.FurnitureService;
import ru.arrowin.bedstoremanager.services.WorkerService;
import ru.arrowin.bedstoremanager.services.imp.SendBotMessageServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@Component
@Log4j
public class TelegramBotController extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";
    @Value("${telegram.bot.name}") private String botName;
    @Value("${telegram.bot.token}") private String botToken;

    private final WorkerService workerService;
    private final FurnitureService furnitureService;

    private final CommandContainer commandContainer;

    public TelegramBotController(WorkerService workerService, FurnitureService furnitureService) {
        this.workerService = workerService;
        this.furnitureService = furnitureService;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
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
            if (update.getMessage().hasText()) {
                Long id = update.getMessage().getChatId();
                String message = update.getMessage().getText();
                if (message.startsWith(COMMAND_PREFIX)) {
                    commandContainer.retrieveCommand(message).execute(update);
                } else if (workerService.isCreating(id)) {
                    sendMessage(new SendMessage(id.toString(),
                                                workerService.createWorkerBySteps(id, workerService.getStep(id),
                                                                                  message)));
                }
            }
        }
        if (update.hasCallbackQuery()) {
            String message = update.getCallbackQuery().getData();
            if (message.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(message).execute(update);
            }
        }
    }

/*   @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            var originalMessage = update.getMessage();
            log.debug(originalMessage.getText());
            log.debug(originalMessage.getFrom().toString());
            if (update.getMessage().hasText()) {
                long id = originalMessage.getFrom().getId();
                if (workerService.isCreating(id)) {
                    String answer = originalMessage.getText();
                    sendMessage(new SendMessage(originalMessage.getChatId().toString(),
                                                workerService.createWorkerBySteps(id, workerService.getStep(id),
                                                                                  answer)));
                } else if (originalMessage.getText().toLowerCase().contains("клавиат")) {
                    sendInlineKeyBoardMessage(originalMessage.getChatId());
            }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery query = update.getCallbackQuery();
            keyBoardHandling(query);
        }
    }*/

    public void sendInlineKeyBoardMessage(long chanId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Добавление нового сотрудника");
        button1.setCallbackData("/add");
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Вывод всех сотрудников");
        button2.setCallbackData("/allWorker");
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Добавить новую мебель");
        button3.setCallbackData("/addFurniture");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow1.add(button1);
        keyboardButtonsRow2.add(button2);
        keyboardButtonsRow3.add(button3);
        List<List<InlineKeyboardButton>> buttonsColons = new ArrayList<>();
        buttonsColons.add(keyboardButtonsRow1);
        buttonsColons.add(keyboardButtonsRow2);
        buttonsColons.add(keyboardButtonsRow3);
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
        String answer;
        switch (query.getData()) {
            case "/allWorker" -> answer = workerService.readAll().toString();
            case "/add" -> {
                Long id = query.getFrom().getId();
                answer = workerService.createWorkerBySteps(id, -1, " ");
            }
            case "/addFurniture" -> {
                furnitureService.add(new Furniture("Шкаф", 500));
                answer = furnitureService.readAll().toString();

            }
            default -> {
                answer = "Что-то пошло не так";
                log.debug(query.getData());
            }
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
