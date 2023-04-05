package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.constructed.MainMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;

//Команда для вывода всех доступных команд пользователю.
@Component
public class HelpCommand extends Command {
    private final SendBotMessageService sendBotMessageService;
    private final WorkerService workerService;

    private final static String HELP_MESSAGE =
            "Я сейчас выведу клавиатуру со всеми возможными командами, которые " + "доступны вам";

    public HelpCommand(SendBotMessageService sendBotMessageService, WorkerService workerService) {
        super(CommandName.HELP);
        this.sendBotMessageService = sendBotMessageService;
        this.workerService = workerService;
    }


    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(HELP_MESSAGE);
        message.setReplyMarkup(new MainMenuKeyBoard(workerService).getKeyBoard(update));
        sendBotMessageService.sendMessage(message);
    }
}
