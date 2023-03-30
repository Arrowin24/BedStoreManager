package ru.arrowin.bedstoremanager.command.get;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.create_furniture.CreatedTodayOtherWorkKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

/*
 * Команда для вывода клавиатуры со списком выполненных сегодня иных работ пользователем телеграмма.
 * Вызывается нажатием на кнопку "Удалить иную работу"
 * */

@Component
public class GetCreatedTodayOtherWorkCommand extends Command {
    private final static String PREVIEW = "Выберите сделанную иную работу:\n";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedOtherWorkService createdOtherWorkService;

    public GetCreatedTodayOtherWorkCommand(SendBotMessageService sendBotMessageService, CreatedOtherWorkService createdOtherWorkService) {
        super(CommandName.GET_CREATED_TODAY_OTHER_WORK);
        this.sendBotMessageService = sendBotMessageService;
        this.createdOtherWorkService = createdOtherWorkService;
    }

    @Override
    public void execute(Update update) {
        Long userId= getId(update);
        SendMessage message = new SendMessage();
        message.setChatId(userId);
        message.setText(PREVIEW);
        message.setReplyMarkup(new CreatedTodayOtherWorkKeyBoard(createdOtherWorkService).getKeyBoard(userId));
        sendBotMessageService.sendMessage(message);
    }
}
