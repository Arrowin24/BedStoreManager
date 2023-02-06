package ru.arrowin.bedstoremanager.command;

import lombok.extern.log4j.Log4j;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {
    void execute(Update update);

    default long getId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new RuntimeException("Проблема с установкой Id");
    }
}
