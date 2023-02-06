package ru.arrowin.bedstoremanager.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandBehavior {
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
