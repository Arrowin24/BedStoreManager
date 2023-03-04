package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;
import ru.arrowin.bedstoremanager.models.answers.CreatedSmallFurniture;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.CreatedSmallFurnitureService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

import java.time.LocalDate;

@Component
public class GetSalaryTodayCommand extends Command {
    @Value("${symbol.for.split}")
    private String SPLIT;
    private final static String PREVIEW = "Сегодня вы заработали : ";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService createdBedsService;
    private final CreatedOtherWorkService createdOtherWorkService;
    private final CreatedSmallFurnitureService createdSmallFurnitureService;

    public GetSalaryTodayCommand(SendBotMessageService sendBotMessageService, CreatedBedsService createdBedsService,
                                 CreatedOtherWorkService createdOtherWorkService,
                                 CreatedSmallFurnitureService createdSmallFurnitureService) {
        super(CommandName.GET_SALARY_TODAY);
        this.sendBotMessageService = sendBotMessageService;
        this.createdBedsService = createdBedsService;
        this.createdOtherWorkService = createdOtherWorkService;
        this.createdSmallFurnitureService = createdSmallFurnitureService;
    }


    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        double totalSumSalary = createdBedsService.getTodayBedSalary(userId)+ createdOtherWorkService.getTodayOtherWorkSalary(userId)+
                createdSmallFurnitureService.getTodaySmallFurnitureSalary(userId);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + totalSumSalary + " рублей");
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
