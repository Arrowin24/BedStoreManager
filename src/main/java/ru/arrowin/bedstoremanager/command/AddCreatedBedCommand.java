package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class AddCreatedBedCommand extends Command {

    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы сделали кровать ";
    private final SendBotMessageService sendBotMessageService;

    public AddCreatedBedCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.ADD_CREATED_BED);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        System.out.println(SPLIT);
        SendMessage message = new SendMessage();
        String text = update.getCallbackQuery().getData().split(SPLIT)[1];
        message.setChatId(getId(update));
        message.setText(PREVIEW + text);
        sendBotMessageService.sendMessage(message);
    }
}
