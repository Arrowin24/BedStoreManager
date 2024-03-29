package ru.arrowin.bedstoremanager.keyboard.create_furniture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.KeyBoard;
import ru.arrowin.bedstoremanager.services.CreatedOtherWorkService;

import java.util.ArrayList;
import java.util.List;

/***
 * Клавитару вывода иной работы ввиде списка для дальнейшего удаления
 */
@Component
public class CreatedTodayOtherWorkKeyBoard implements KeyBoard {
    @Value(value = "${symbol.for.split}") private String SPLIT;
    private final CreatedOtherWorkService createdOtherWorkService;

    public CreatedTodayOtherWorkKeyBoard(CreatedOtherWorkService createdOtherWorkService) {
        this.createdOtherWorkService = createdOtherWorkService;
    }


    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        return null;
    }

    public InlineKeyboardMarkup getKeyBoard(Long userId) {
        List<String> otherWorkNameAndId = createdOtherWorkService.getOtherWorkNameAndId(userId);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String nameAndId : otherWorkNameAndId) {
            String name = nameAndId.split("&&")[0];
            String id = nameAndId.split("&&")[1];
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(name);
            button.setCallbackData(CommandName.DELETE_OTHER_WORK.getCommandName() + "&&" + id);
            keyboard.add(createButtonsLine(button));
        }
        InlineKeyboardButton button1 = createButton("Вернуться в меню", CommandName.BACK_TO_MENU);
        keyboard.add(createButtonsLine(button1));
        return new InlineKeyboardMarkup(keyboard);
    }
}
