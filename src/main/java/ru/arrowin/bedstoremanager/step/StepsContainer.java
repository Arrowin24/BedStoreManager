package ru.arrowin.bedstoremanager.step;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StepsContainer {
    private final Map<Long, Step> steps = new HashMap<>();

    UnknownStep unknownStep;

    public StepsContainer(@Lazy UnknownStep unknownStep) {
        this.unknownStep = unknownStep;
    }

    public Step getStep(Update update) {
        return steps.getOrDefault(getId(update), unknownStep);
    }

    public void putStep(long id, Step step) {
        steps.put(id, step);
    }

    public boolean isContains(Update update) {
        return steps.containsKey(getId(update));
    }

    public void deleteStep(long id) {
        steps.remove(id);
    }

    private long getId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new RuntimeException("Проблема с установкой Id");
    }

}
