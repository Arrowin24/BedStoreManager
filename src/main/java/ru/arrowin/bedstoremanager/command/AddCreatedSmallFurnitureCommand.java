package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class AddCreatedSmallFurnitureCommand extends Command{

    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы сделали малую мебель  ";
    private final SendBotMessageService sendBotMessageService;

    public AddCreatedSmallFurnitureCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.ADD_SMALL_FURNITURE);
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
