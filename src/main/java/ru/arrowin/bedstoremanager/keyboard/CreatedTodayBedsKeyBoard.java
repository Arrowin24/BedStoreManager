package ru.arrowin.bedstoremanager.keyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreatedTodayBedsKeyBoard implements KeyBoard {

    @Value(value = "${symbol.for.split}") private String SPLIT;
    private final CreatedBedsService createdBedsService;

    public CreatedTodayBedsKeyBoard(CreatedBedsService createdBedsService) {
        this.createdBedsService = createdBedsService;
    }

    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        return null;
    }

    public InlineKeyboardMarkup getKeyBoard(Long userId) {
        List<String> bedsNameAndId = createdBedsService.getBedsNameAndId(userId);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String nameAndId : bedsNameAndId) {
            String name = nameAndId.split("&&")[0];
            String id = nameAndId.split("&&")[1];
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(name);
            button.setCallbackData(CommandName.DELETE_BED.getCommandName() + "&&" + id);
            keyboard.add(createButtonsLine(button));
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
