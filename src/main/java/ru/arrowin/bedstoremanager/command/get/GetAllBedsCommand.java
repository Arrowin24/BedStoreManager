package ru.arrowin.bedstoremanager.command.get;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.command.Command;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.furniture.BedsKeyBoard;
import ru.arrowin.bedstoremanager.services.BedService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;


/*
* Команда для вывода в telegram клавиатуры со всем списком доступных кроватей на предприятии.
* Необходима для дальнейшего сохранения изготовленной кровати.
* Вызывается нажатием на кнопку "Добавить кровать".
* */
@Component
public class GetAllBedsCommand extends Command {
    private final static String PREVIEW = "Выберите сделанную кровать:\n";
    private final SendBotMessageService sendBotMessageService;
    private final BedService bedService;

    public GetAllBedsCommand(SendBotMessageService sendBotMessageService, BedService bedService) {
        super(CommandName.GET_ALL_BEDS);
        this.sendBotMessageService = sendBotMessageService;
        this.bedService = bedService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW);
        message.setReplyMarkup(new BedsKeyBoard(bedService).getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }
}
