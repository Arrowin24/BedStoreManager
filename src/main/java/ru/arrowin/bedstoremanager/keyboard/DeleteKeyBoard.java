package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;

public class DeleteKeyBoard implements KeyBoard{
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Удалить Кровать", CommandName.);
        InlineKeyboardButton button2 = createButton("Удалить Малую мебель", CommandName.);
        InlineKeyboardButton button3 = createButton("Удалить иную работу", CommandName.);
        InlineKeyboardButton button4 = createButton("Вернуться в меню", CommandName.BACK_TO_MENU);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        keyboard.add(createButtonsLine(button4));

        return new InlineKeyboardMarkup(keyboard);
    }
}
