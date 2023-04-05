package ru.arrowin.bedstoremanager.command;

import org.telegram.telegrambots.meta.api.objects.Update;


public interface CommandBehavior {

    // Исполняющий метода для реализации команды
    void execute(Update update);

    //Вспомогающий метод для получения id пользователя
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
