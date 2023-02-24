package ru.arrowin.bedstoremanager.keyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.models.answers.OtherWork;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;
import ru.arrowin.bedstoremanager.services.OtherWorkService;

import java.util.ArrayList;
import java.util.List;

@Component
public class OtherWorkKeyBoard implements KeyBoard{
    @Value(value = "${symbol.for.split}") private String SPLIT;
    private final OtherWorkService otherWorkService;

    public OtherWorkKeyBoard(OtherWorkService otherWorkService) {
        this.otherWorkService = otherWorkService;
    }

    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        List<OtherWork> otherWorks = otherWorkService.readAll();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (OtherWork otherWork : otherWorks) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(otherWork.getName());
            button.setCallbackData(CommandName.ADD_OTHER_WORK.getCommandName()+"&&"+otherWork.getId());
            keyboard.add(createButtonsLine(button));
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
