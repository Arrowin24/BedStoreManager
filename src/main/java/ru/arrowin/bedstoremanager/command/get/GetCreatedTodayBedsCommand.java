package ru.arrowin.bedstoremanager.command.get;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.create_furniture.CreatedTodayBedsKeyBoard;

import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

/*
* Команда для вывода клавиатуры со списком изготовленных сегодня кроватей пользователем телеграмма.
* Вызывается нажатием на кнопку "Удалить кровать"
* */

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
