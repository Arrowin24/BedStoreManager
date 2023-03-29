package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.CreatedTodayBedsKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.CreatedTodaySmallFurnitureKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
@Component
public class GetCreatedSmallFurnitureCommand extends Command{
    private final static String PREVIEW = "Выберите сделанную малую мебель:\n";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedSmallFurnitureService createdSmallFurnitureService;

    public GetCreatedSmallFurnitureCommand(SendBotMessageService sendBotMessageService, CreatedSmallFurnitureService createdSmallFurnitureService) {
        super(CommandName.GET_CREATED_TODAY_SMALL_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
    }


    @Override
    public void execute(Update update) {
        Long userId= getId(update);
        SendMessage message = new SendMessage();
        message.setChatId(userId);
        message.setText(PREVIEW);
        message.setReplyMarkup(new CreatedTodaySmallFurnitureKeyBoard(createdSmallFurnitureService).getKeyBoard(userId));
        sendBotMessageService.sendMessage(message);
    }
}
