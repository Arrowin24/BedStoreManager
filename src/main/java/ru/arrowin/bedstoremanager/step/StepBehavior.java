package ru.arrowin.bedstoremanager.step;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface StepBehavior {

    void startStep(Update update);
    void doStep(Update update);

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
