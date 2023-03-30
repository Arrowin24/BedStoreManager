package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;
//Клвиатура для выбора изделия , которое нужно удалить из списка сделнных работ
public class DeleteKeyBoard implements KeyBoard{
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Удалить Кровать", CommandName.GET_CREATED_TODAY_BEDS);
        InlineKeyboardButton button2 = createButton("Удалить Малую мебель", CommandName.GET_CREATED_TODAY_SMALL_FURNITURE);
        InlineKeyboardButton button3 = createButton("Удалить иную работу", CommandName.GET_CREATED_TODAY_OTHER_WORK);
        InlineKeyboardButton button4 = createButton("Вернуться в меню", CommandName.BACK_TO_MENU);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        keyboard.add(createButtonsLine(button4));

        return new InlineKeyboardMarkup(keyboard);
    }
}
