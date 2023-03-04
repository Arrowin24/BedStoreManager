package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class GetAllFurnitureTodayCommand extends Command {

    private final static String GET_FURNITURE_TODAY_MASSAGE = "Вывод сделанной работы за день";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService createdBedsService;
    private final CreatedSmallFurnitureService createdSmallFurnitureService;
    private final CreatedOtherWorkService createdOtherWorkService;

    public GetAllFurnitureTodayCommand(SendBotMessageService sendBotMessageService, CreatedBedsService createdBedsService, CreatedSmallFurnitureService createdSmallFurnitureService, CreatedOtherWorkService createdOtherWorkService) {
        super(CommandName.GET_FURNITURE_TODAY);
        this.sendBotMessageService = sendBotMessageService;
        this.createdBedsService = createdBedsService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
        this.createdOtherWorkService = createdOtherWorkService;
    }

    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(GET_FURNITURE_TODAY_MASSAGE + "\n Кроватей: " + createdBedsService.getBedsTodayByAmount(userId)
                + "\n Малой мебели: " + createdSmallFurnitureService.getSmallFurnitureTodayByAmount(userId) +
                "\n Иных работ: " + createdOtherWorkService.getOtherWorkTodayByAmount(userId));
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
