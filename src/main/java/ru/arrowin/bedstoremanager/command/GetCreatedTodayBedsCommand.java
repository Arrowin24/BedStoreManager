package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.CreatedTodayBedsKeyBoard;

import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class GetCreatedTodayBedsCommand extends Command {

    private final static String PREVIEW = "Выберите сделанную кровать:\n";

    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService bedService;

    public GetCreatedTodayBedsCommand( SendBotMessageService sendBotMessageService, CreatedBedsService bedService) {
        super(CommandName.GET_CREATED_TODAY_BEDS);
        this.sendBotMessageService=sendBotMessageService;
        this.bedService=bedService;
    }

    @Override
    public void execute(Update update) {
        Long userId= getId(update);
        SendMessage message = new SendMessage();
        message.setChatId(userId);
        message.setText(PREVIEW);
        message.setReplyMarkup(new CreatedTodayBedsKeyBoard(bedService).getKeyBoard(userId));
        sendBotMessageService.sendMessage(message);
    }
}
