package ru.arrowin.bedstoremanager.keyboard.position;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.KeyBoard;

import java.util.ArrayList;
import java.util.List;

public class ChiefEngineerKeyBoard implements KeyBoard {

    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Добавить новую мебель", CommandName.CREATE_FURNITURE);
        InlineKeyboardButton button2 = createButton("Вывести список выполненых сегодня работ",
                                                    CommandName.GET_FURNITURE_TODAY);
        InlineKeyboardButton button3 = createButton("Посчитать заработок за сегодня", CommandName.GET_SALARY_TODAY);
        InlineKeyboardButton button4 = createButton("Вывести всех сотрудников", CommandName.GET_ALL_WORKERS);
  /*      InlineKeyboardButton button5 = createButton("Добавить кровать", CommandName.GET_ALL_BEDS);
        InlineKeyboardButton button6 = createButton("Добавить малую мебель", CommandName.GET_ALL_SMALL_FURNITURE);
        InlineKeyboardButton button7 = createButton("Добавить иную работу", CommandName.GET_ALL_OTHER_WORK);*/

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
        keyboard.add(createButtonsLine(button2));
        keyboard.add(createButtonsLine(button3));
        keyboard.add(createButtonsLine(button4));

        return new InlineKeyboardMarkup(keyboard);
    }
}
