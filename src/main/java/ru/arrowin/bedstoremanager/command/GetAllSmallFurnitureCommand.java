package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.BedsKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.SmallFurnitureKeyBoard;
import ru.arrowin.bedstoremanager.services.BedService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.SmallFurnitureService;

@Component
public class GetAllSmallFurnitureCommand extends Command {
    private final static String PREVIEW = "Выберите малую мебель:\n";
    private final SendBotMessageService sendBotMessageService;
    private final SmallFurnitureService smallFurnitureService;

    public GetAllSmallFurnitureCommand(SendBotMessageService sendBotMessageService, SmallFurnitureService smallFurnitureService) {
        super(CommandName.GET_ALL_SMALL_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
        this.smallFurnitureService = smallFurnitureService;
    }


    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW);
        message.setReplyMarkup(new SmallFurnitureKeyBoard(smallFurnitureService).getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
