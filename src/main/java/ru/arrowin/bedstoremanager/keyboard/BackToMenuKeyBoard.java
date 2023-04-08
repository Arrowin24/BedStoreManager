package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;
/***
 * Клавиатура для реализации кнопки "Вернуться в меню"
 */
public class BackToMenuKeyBoard implements KeyBoard {

    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Вернуться в меню", CommandName.BACK_TO_MENU);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        return new InlineKeyboardMarkup(keyboard);
    }
}
