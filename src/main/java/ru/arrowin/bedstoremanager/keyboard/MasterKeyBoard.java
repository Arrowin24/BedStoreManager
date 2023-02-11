package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;

public class MasterKeyBoard implements KeyBoard{
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 =  createButton("Вывести всех сотрудников" , CommandName.GET_ALL_WORKERS);
        InlineKeyboardButton button2 =  createButton("Добавить новую мебель" , CommandName.CREATE_FURNITURE);
        InlineKeyboardButton button3 =  createButton("Вывод выполненных работ" , CommandName.GET_ALL_WORK_TODAY);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        return new InlineKeyboardMarkup(keyboard);
    }
}
