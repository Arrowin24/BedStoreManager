package ru.arrowin.bedstoremanager.step;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class UnknownStep extends Step {
    public UnknownStep(StepsContainer container, SendBotMessageService sendBotMessageService) {
        super(container, sendBotMessageService);
    }

    @Override
    public void startStep(Update update) {

    }

    @Override
    public void doStep(Update update) {
        long id = update.getMessage().getChatId();
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText("Моя тебя не понимать");
        getSendBotMessageService().sendMessage(message);
    }
}
