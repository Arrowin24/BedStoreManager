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
        InlineKeyboardButton button2 = createButton("Вывести список выполненых сегодня работ",
                                                    CommandName.GET_FURNITURE_TODAY);
        InlineKeyboardButton button1 = createButton("Зарплата за сегодня", CommandName.GET_SALARY_TODAY);
        InlineKeyboardButton button4 = createButton("Добавить сделанную работу", CommandName.ADD_CREATED);
        InlineKeyboardButton button3 = createButton("Зарплата за текущий месяц",CommandName.GET_SALARY_BY_CURRENT_MONTH);
        InlineKeyboardButton button5 = createButton("Удалить кровать из списка работ",CommandName.DELETE_BED);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        keyboard.add(createButtonsLine(button4));
        keyboard.add(createButtonsLine(button5));

        return new InlineKeyboardMarkup(keyboard);
    }
}
