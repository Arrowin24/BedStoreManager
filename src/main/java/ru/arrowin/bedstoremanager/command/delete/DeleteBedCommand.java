package ru.arrowin.bedstoremanager.command.delete;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.BackToMenuKeyBoard;
import ru.arrowin.bedstoremanager.services.*;

/*
* Команда, удаляющая выбранную из перечесления изготовленную ранее кровать.
* Вызывается по нажатию на кнопку с названием изготовленной ранее кровати, которую хотят удалить.
* */

@Component
public class DeleteBedCommand extends Command {
    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы успешно удалили кровать!";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService bedService;


    public DeleteBedCommand(SendBotMessageService sendBotMessageService, CreatedBedsService bedService) {
        super(CommandName.DELETE_BED);
        this.sendBotMessageService = sendBotMessageService;
        this.bedService = bedService;
    }

    @Override
    public void execute(Update update) {
        int bedId = Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        bedService.delete(bedId);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW);
        message.setReplyMarkup(new BackToMenuKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);

    }
}
