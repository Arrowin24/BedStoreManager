package ru.arrowin.bedstoremanager.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;

import java.util.ArrayList;
import java.util.List;
/***
 * Стартовая клавиатура для начала использования приложением и регистрации в нем
 */
public class StartKeyBoard implements KeyBoard {
    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        InlineKeyboardButton button1 = createButton("Зарегистрироваться", CommandName.CREATE_WORKER);
  //      InlineKeyboardButton button2 = createButton("Авторизоваться", CommandName.LOGIN);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(createButtonsLine(button1));
  //     keyboard.add(createButtonsLine(button2));
        return new InlineKeyboardMarkup(keyboard);
    }
}
