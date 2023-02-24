package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.BedsKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.OtherWorkKeyBoard;
import ru.arrowin.bedstoremanager.services.BedService;
import ru.arrowin.bedstoremanager.services.OtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class GetAllOtherWorkCommand extends Command{
    private final static String PREVIEW = "Выберите иную работу:\n";
    private final SendBotMessageService sendBotMessageService;
    private final OtherWorkService otherWorkService;

    public GetAllOtherWorkCommand(SendBotMessageService sendBotMessageService,OtherWorkService otherWorkService) {
        super(CommandName.GET_ALL_OTHER_WORK);
        this.sendBotMessageService = sendBotMessageService;
        this.otherWorkService = otherWorkService;
    }


    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW);
        message.setReplyMarkup(new OtherWorkKeyBoard(otherWorkService).getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
