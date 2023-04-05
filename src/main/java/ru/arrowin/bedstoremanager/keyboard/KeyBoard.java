package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.Arrays;
import java.util.List;

//Интерфейс для реализации методов клавиатуры
public interface KeyBoard {

    InlineKeyboardMarkup getKeyBoard();

    default InlineKeyboardButton createButton(String text, CommandName command) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(command.getCommandName());
        return button;
    }

    default List<InlineKeyboardButton> createButtonsLine(InlineKeyboardButton... button) {
        return Arrays.stream(button).toList();
    }

}
