package ru.arrowin.bedstoremanager.keyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.services.BedService;

import java.util.ArrayList;
import java.util.List;

@Component
public class BedsKeyBoard implements KeyBoard {

    @Value(value = "${symbol.for.split}") private String SPLIT;
    private final BedService bedService;

    public BedsKeyBoard(BedService bedService) {
        this.bedService = bedService;
    }

    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        List<Bed> beds = bedService.readAll();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (Bed bed : beds) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(bed.getName());
            button.setCallbackData(CommandName.ADD_CREATED_BED.getCommandName()+"&&"+bed.getId());
            keyboard.add(createButtonsLine(button));
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
