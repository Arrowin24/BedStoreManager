package ru.arrowin.bedstoremanager.command.delete;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

/*
 * Команда, удаляющая выбранную из перечесления выполненную ранее другую работу.
 * Вызывается по нажатию на кнопку с названием выполненной ранее другой работой, которую хотят удалить.
 * */

@Component
public class DeleteOtherWorkCommand  extends Command {
    @Value("${symbol.for.split}")
    private String SPLIT;
    private final static String PREVIEW = "Вы успешно удалили иную работу!";

    private final SendBotMessageService sendBotMessageService;

    private final CreatedOtherWorkService createdOtherWorkService;

    public DeleteOtherWorkCommand(SendBotMessageService sendBotMessageService, CreatedOtherWorkService createdOtherWorkService) {
        super(CommandName.DELETE_OTHER_WORK);
        this.sendBotMessageService = sendBotMessageService;
        this.createdOtherWorkService = createdOtherWorkService;
    }


    @Override
    public void execute(Update update) {
        int otherWorkId = Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        createdOtherWorkService.delete(otherWorkId);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW);
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);

    }
}
