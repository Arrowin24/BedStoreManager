package ru.arrowin.bedstoremanager.command.get;

import org.springframework.beans.factory.annotation.Value;
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
@Component
public class GetSalaryByCurrentMonthCommand extends Command {

    @Value("${symbol.for.split}")
    private String SPLIT;
    private final static String PREVIEW = "Зарплата за месяц : ";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService createdBedsService;
    private final CreatedOtherWorkService createdOtherWorkService;
    private final CreatedSmallFurnitureService createdSmallFurnitureService;


    public GetSalaryByCurrentMonthCommand( SendBotMessageService sendBotMessageService, CreatedBedsService createdBedsService, CreatedOtherWorkService createdOtherWorkService, CreatedSmallFurnitureService createdSmallFurnitureService) {
        super(CommandName.GET_SALARY_BY_CURRENT_MONTH);
        this.sendBotMessageService = sendBotMessageService;
        this.createdBedsService = createdBedsService;
        this.createdOtherWorkService = createdOtherWorkService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
    }

    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        double totalMonthSalary = createdBedsService.getCurrentMonthBedSalary(userId) + createdOtherWorkService.getCurrentMonthOtherWorkSalary(userId)
                + createdSmallFurnitureService.getCurrentMonthSmallFurnitureSalary(userId);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + totalMonthSalary + " рублей");
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
