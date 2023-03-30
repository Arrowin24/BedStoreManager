package ru.arrowin.bedstoremanager.command.delete;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
@Component
public class DeleteSmallFurnitureCommand extends Command {
    @Value("${symbol.for.split}")
    private String SPLIT;
    private final static String PREVIEW = "Вы успешно удалили малую мебель!";

    private final SendBotMessageService sendBotMessageService;

    private final CreatedSmallFurnitureService createdSmallFurnitureService;

    public DeleteSmallFurnitureCommand(SendBotMessageService sendBotMessageService, CreatedSmallFurnitureService createdSmallFurnitureService) {
        super(CommandName.DELETE_SMALL_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
    }


    @Override
    public void execute(Update update) {
        int smallFurnitureId = Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        createdSmallFurnitureService.delete(smallFurnitureId);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW);
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);

    }
}
