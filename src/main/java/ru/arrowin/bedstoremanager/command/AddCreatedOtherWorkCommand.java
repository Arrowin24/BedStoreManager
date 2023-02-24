package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
@Component
public class AddCreatedOtherWorkCommand extends Command{
    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы сделали иную работу ";
    private final SendBotMessageService sendBotMessageService;


    public AddCreatedOtherWorkCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.ADD_OTHER_WORK);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        String text = update.getCallbackQuery().getData().split(SPLIT)[1];
        message.setChatId(getId(update));
        message.setText(PREVIEW + text);
        sendBotMessageService.sendMessage(message);
    }
}
