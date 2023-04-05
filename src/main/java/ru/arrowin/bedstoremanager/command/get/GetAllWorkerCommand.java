package ru.arrowin.bedstoremanager.command.get;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;

import java.util.stream.Collectors;

/*
 * Команда для вывода списка всех рарегистрированных в приложении пользователей
 * */
@Component
public class GetAllWorkerCommand extends Command {
    private final static String PREVIEW = "Список всех рабочих:\n";

    private final WorkerService workerService;
    private final SendBotMessageService sendBotMessageService;

    public GetAllWorkerCommand(WorkerService workerService, SendBotMessageService sendBotMessageService) {
        super(CommandName.GET_ALL_WORKERS);
        this.workerService = workerService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String workers = workerService.readAll().stream().map(Worker::toText).collect(Collectors.joining("\n"));
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + workers);
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
