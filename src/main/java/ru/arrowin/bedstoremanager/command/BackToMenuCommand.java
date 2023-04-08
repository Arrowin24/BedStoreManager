package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.constructed.MainMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;

/**
* Команда для перехода в главное меню пользователя.
* Вызывается кнопкой "Вернуться в меню"
* */
@Component
public class BackToMenuCommand extends Command {

    private final SendBotMessageService sendBotMessageService;
    private final WorkerService workerService;

    public BackToMenuCommand(SendBotMessageService sendBotMessageService, WorkerService workerService) {
        super(CommandName.BACK_TO_MENU);
        this.sendBotMessageService = sendBotMessageService;
        this.workerService = workerService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText("Выберите команду из списка:");
        message.setReplyMarkup(new MainMenuKeyBoard(workerService).getKeyBoard(update));
        sendBotMessageService.sendMessage(message);
    }
}
