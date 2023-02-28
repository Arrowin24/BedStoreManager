package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
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
    private final static String PREVIEW = "Сегодня вы сделали кровати : ";

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
        int bedId = Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        LocalDate today = LocalDate.now();
        CreatedBed bed = new CreatedBed(userId, bedId, today);
        int otherWorkId = Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        LocalDate today1 = LocalDate.now();
        CreatedOtherWork otherWork = new CreatedOtherWork(userId, otherWorkId, today1);
        int smallFurnitureId = Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        LocalDate today2 = LocalDate.now();
        CreatedSmallFurniture smallFurniture = new CreatedSmallFurniture(userId, smallFurnitureId, today2);
        createdSmallFurnitureService.add(smallFurniture);
        createdOtherWorkService.add(otherWork);
        createdBedsService.add(bed);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + createdBedsService.getTodayCreatedBeds(userId) + "\n" + "Иные раблоты: " + createdOtherWorkService.getTodayCreatedOtherWork(userId)
                + "\n" + "Малую мебель:  " + createdSmallFurnitureService.getTodayCreatedSmallFurniture(userId) + "\n" + "Сегодня вы заработали : " + createdBedsService.getTodayBedSalary(userId) +
                createdOtherWorkService.getTodayOtherWorkSalary(userId) + createdSmallFurnitureService.getTodaySmallFurnitureSalary(userId));
        sendBotMessageService.sendMessage(message);
    }
}
