package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;

public class WorkerKeyBoard implements KeyBoard {
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Добавит новую мебель", CommandName.ADD_FURNITURE);
        InlineKeyboardButton button2 = createButton("Вывести список выполненых сегодня работ",
                                                    CommandName.GET_FURNITURE_TODAY);
        InlineKeyboardButton button3 = createButton("Посчитать заработок за сегодня", CommandName.GET_SALARY_TODAY);
        InlineKeyboardButton button4 = createButton("Вывести всех сотрудников", CommandName.GET_ALL_WORKERS);
        InlineKeyboardButton button5 = createButton("Добавить сделанную кровать", CommandName.GET_ALL_BEDS);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        keyboard.add(createButtonsLine(button4));
        keyboard.add(createButtonsLine(button5));
        return new InlineKeyboardMarkup(keyboard);
    }
}
