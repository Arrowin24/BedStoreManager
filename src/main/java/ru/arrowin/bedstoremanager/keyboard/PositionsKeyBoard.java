package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.models.Position;

import java.util.ArrayList;
import java.util.List;
/***
 * Клавиатура для вывода позиции сотрудника предприятия
 */
public class PositionsKeyBoard implements KeyBoard {
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (Position position : Position.values()) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(position.name());
            button.setCallbackData(position.name());
            keyboard.add(createButtonsLine(button));
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
