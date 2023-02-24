package ru.arrowin.bedstoremanager.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.StartKeyBoard;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Component
public class StartCommand extends Command {

    private final SendBotMessageService sendBotMessageService;


    private final static String START_MESSAGE = "Привет. Я телеграм бот, который поможет тебе следить за проделанной "
            + "работой. " + "Поэтому если ты первый раз пользуешься мной, то нажми кнопку 'Зарегистрироваться'," + " "
            + "если ты уже использовал меня, то нажми кнопку 'Авторизоавться'";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        super(CommandName.START);
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(START_MESSAGE);
        message.setReplyMarkup(new StartKeyBoard().getKeyBoard());
        sendBotMessageService.sendMessage(message);
    }

}
