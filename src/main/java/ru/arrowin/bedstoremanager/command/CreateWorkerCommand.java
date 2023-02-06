package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;

public class CreateWorkerCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final WorkerService workerService;

    public CreateWorkerCommand(SendBotMessageService sendBotMessageService, WorkerService workerService) {
        this.sendBotMessageService = sendBotMessageService;
        this.workerService = workerService;
    }

    @Override
    public void execute(Update update) {
        long id = getId(update);
        String request = workerService.createWorkerBySteps(id,-1," ");
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(request);
        sendBotMessageService.sendMessage(message);
    }
}
