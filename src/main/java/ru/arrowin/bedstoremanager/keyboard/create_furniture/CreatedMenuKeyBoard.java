package ru.arrowin.bedstoremanager.keyboard.create_furniture;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.KeyBoard;

import java.util.ArrayList;
import java.util.List;

public class CreatedMenuKeyBoard implements KeyBoard {
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Добавить кровать", CommandName.GET_ALL_BEDS);
        InlineKeyboardButton button2 = createButton("Добавить малую мебель", CommandName.GET_ALL_SMALL_FURNITURE);
        InlineKeyboardButton button3 = createButton("Добавить иную работу", CommandName.GET_ALL_OTHER_WORK);
        InlineKeyboardButton button4 = createButton("Вернуться в меню", CommandName.BACK_TO_MENU);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        keyboard.add(createButtonsLine(button4));

        return new InlineKeyboardMarkup(keyboard);
    }
}
