package ru.arrowin.bedstoremanager.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

public class GetAllWorkTodayCommand extends Command{

    private final static String WORK_TODAY_MASSAGE = "Вывод выполненной работы за сегодня ";

    private final SendBotMessageService sendBotMessageService;

    public GetAllWorkTodayCommand( SendBotMessageService sendBotMessageService) {
        super(CommandName.GET_ALL_WORK_TODAY);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

    }
}
