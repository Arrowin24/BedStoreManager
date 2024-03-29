package ru.arrowin.bedstoremanager.command.get;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.furniture.OtherWorkKeyBoard;
import ru.arrowin.bedstoremanager.services.OtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

/*
 * Команда для вывода в telegram клавиатуры со всем списком доступных иных работ на предприятии.
 * Необходима для дальнейшего сохранения выполненых иных работ.
 * Вызывается нажатием на кнопку "Добавить иную работу".
 * */
@Component
public class GetAllOtherWorkCommand extends Command {
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
