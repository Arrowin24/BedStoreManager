package ru.arrowin.bedstoremanager.command.addcreate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.models.answers.CreatedOtherWork;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

import java.time.LocalDate;


/*
*  Команда, осуществляющая доблавление сделанной иной работы в базу данных и выводящая полученную прибыль по иным работам
* за сегодня.
* Вызывается нажатием на кнопку с названием иной работы из списка доступных работ.
* */
@Component
public class AddCreatedOtherWorkCommand extends Command {
    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы сделали иную работу ";
    private final SendBotMessageService sendBotMessageService;
    private final CreatedOtherWorkService createdOtherWorkService;


    public AddCreatedOtherWorkCommand(SendBotMessageService sendBotMessageService, CreatedOtherWorkService createdOtherWorkService) {
        super(CommandName.ADD_CREATED_OTHER_WORK);
        this.sendBotMessageService = sendBotMessageService;
        this.createdOtherWorkService = createdOtherWorkService;
    }

    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        int otherWorkId =Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        LocalDate today = LocalDate.now();
        CreatedOtherWork otherWork = new CreatedOtherWork(userId,otherWorkId,today);
        createdOtherWorkService.add(otherWork);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + createdOtherWorkService.getTodayCreatedOtherWork(userId) +"\n"+"Сегодня вы заработали на иной работе: "+createdOtherWorkService.getTodayOtherWorkSalary(userId));
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
