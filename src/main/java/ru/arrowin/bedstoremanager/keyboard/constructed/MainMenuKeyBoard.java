package ru.arrowin.bedstoremanager.keyboard.constructed;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.arrowin.bedstoremanager.keyboard.position.AdminKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.position.ChiefEngineerKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.position.WorkerKeyBoard;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.services.WorkerService;
//Клаиатура по созданию должности
public class MainMenuKeyBoard {

    private final WorkerService workerService;

    public MainMenuKeyBoard(WorkerService workerService) {
        this.workerService = workerService;
    }

    public InlineKeyboardMarkup getKeyBoard(Update update) {
        Long userId = getId(update);
        Worker worker = workerService.read(userId);
        switch (worker.getPosition()) {
            case ADMIN -> {
                return new AdminKeyBoard().getKeyBoard();
            }
            case WORKER -> {
                return new WorkerKeyBoard().getKeyBoard();
            }
            case CHEF_ENGINEER -> {
                return new ChiefEngineerKeyBoard().getKeyBoard();
            }
            default -> throw new RuntimeException("Проблемы с определением должности");
        }
    }

    private Long getId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        throw new RuntimeException("Проблема с установкой Id");
    }

}
