package ru.arrowin.bedstoremanager.services.imp;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.arrowin.bedstoremanager.controller.TelegramBotController;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

@Service
@Log4j
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final TelegramBotController telegramBot;

    @Autowired
    public SendBotMessageServiceImpl(@Lazy TelegramBotController telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Override
    public void sendMessage(SendMessage message) {
        if (message == null) {
            return;
        }
        try {
            telegramBot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e);
        }
    }

}
