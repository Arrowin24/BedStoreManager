package ru.arrowin.bedstoremanager.keyboard.position;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.KeyBoard;

import java.util.ArrayList;
import java.util.List;

public class WorkerKeyBoard implements KeyBoard {
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Вывести список выполненых сегодня работ",
                                                    CommandName.GET_FURNITURE_TODAY);
        InlineKeyboardButton button2 = createButton("Посчитать заработок за сегодня", CommandName.GET_SALARY_TODAY);
        InlineKeyboardButton button3 = createButton("Добавить сделанную работу", CommandName.ADD_CREATED);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));

        return new InlineKeyboardMarkup(keyboard);
    }
}
