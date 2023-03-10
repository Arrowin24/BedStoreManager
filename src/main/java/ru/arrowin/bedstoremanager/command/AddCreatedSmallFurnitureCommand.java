package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

import java.time.LocalDate;

@Component
public class AddCreatedSmallFurnitureCommand extends Command{

    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы сделали малую мебель  ";
    private final SendBotMessageService sendBotMessageService;
    private final CreatedSmallFurnitureService createdSmallFurnitureService;

    public AddCreatedSmallFurnitureCommand(SendBotMessageService sendBotMessageService, CreatedSmallFurnitureService createdSmallFurnitureService) {
        super(CommandName.ADD_CREATED_SMALL_FURNITURE);
        this.sendBotMessageService = sendBotMessageService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
    }


    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        int smallFurnitureId =Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        LocalDate today = LocalDate.now();
        CreatedSmallFurniture smallFurniture = new CreatedSmallFurniture(userId,smallFurnitureId,today);
        createdSmallFurnitureService.add(smallFurniture);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + createdSmallFurnitureService.getTodayCreatedSmallFurniture(userId) +"\n"+"Сегодня вы заработали на малой мебели: "+createdSmallFurnitureService.getTodaySmallFurnitureSalary(userId));
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }

}
