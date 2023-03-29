package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.BedService;
import ru.arrowin.bedstoremanager.services.OtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.SmallFurnitureService;

@Component
public class DeleteBedCommand extends  Command{

    private final String PREVIEW = "Выберете изделие  из списка";

    private final SendBotMessageService sendBotMessageService;

    private final BedService bedService;
    private final SmallFurnitureService smallFurnitureService;
    private final OtherWorkService otherWorkService;

    public DeleteBedCommand(SendBotMessageService sendBotMessageService, BedService bedService, SmallFurnitureService smallFurnitureService, OtherWorkService otherWorkService) {
        super(CommandName.DELETE_BED);
        this.sendBotMessageService = sendBotMessageService;
        this.bedService = bedService;
        this.smallFurnitureService = smallFurnitureService;
        this.otherWorkService = otherWorkService;
    }

    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));

    }
}
