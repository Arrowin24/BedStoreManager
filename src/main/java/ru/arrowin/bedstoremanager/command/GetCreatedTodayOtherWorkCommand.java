package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.CreatedTodayBedsKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.CreatedTodayOtherWorkKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

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
