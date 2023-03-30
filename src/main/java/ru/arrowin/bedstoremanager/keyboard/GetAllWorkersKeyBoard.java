package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;
//Клавиатура для вывода всех сотрудников в ввиде списка
public class GetAllWorkersKeyBoard implements KeyBoard{
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 =  createButton("Вывести всех сотрудников" , CommandName.GET_ALL_WORKERS);
        InlineKeyboardButton button2 = createButton("Вернуться к командам",CommandName.HELP);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        return new InlineKeyboardMarkup(keyboard);
    }
}
