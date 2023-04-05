package ru.arrowin.bedstoremanager.command.get;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
/*
* Команда для вывода в telegram информации о сделанной работе за сегодня. Показывает количество изделий по категориям.
* */
@Component
public class GetAllWorkTodayCommand extends Command {
    private final static String GET_AlL_WORK_TODAY_MASSAGE = "Вывод сделанной работы за день";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService createdBedsService;
    private final CreatedSmallFurnitureService createdSmallFurnitureService;
    private final CreatedOtherWorkService createdOtherWorkService;

    public GetAllWorkTodayCommand( SendBotMessageService sendBotMessageService, CreatedBedsService createdBedsService, CreatedSmallFurnitureService createdSmallFurnitureService, CreatedOtherWorkService createdOtherWorkService) {
        super(CommandName.GET_ALL_WORK_TODAY);
        this.sendBotMessageService = sendBotMessageService;
        this.createdBedsService = createdBedsService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
        this.createdOtherWorkService = createdOtherWorkService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(GET_AlL_WORK_TODAY_MASSAGE + "\n Кроватей: " + createdBedsService.getAmountBedsForMaster()
                + "\n Малой мебели: " + createdSmallFurnitureService.getAmountSmallFurnitureForMaster() +
                "\n Иных работ: " + createdOtherWorkService.getAmountOtherWorkForMaster());
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
