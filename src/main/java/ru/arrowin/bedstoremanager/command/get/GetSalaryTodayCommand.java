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
 * Команда выводящая в телеграмм сообщение с заработанными средствами за сегодняшний день.
 * Вывывается нажатием на клавиатуру "Посчитать заработок за сегодня"
 * */
@Component
public class GetSalaryTodayCommand extends Command {

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
