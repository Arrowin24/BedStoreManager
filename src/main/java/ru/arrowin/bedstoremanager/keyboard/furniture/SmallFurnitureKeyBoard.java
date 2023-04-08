package ru.arrowin.bedstoremanager.keyboard.furniture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.command.CommandName;
import ru.arrowin.bedstoremanager.keyboard.KeyBoard;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.answers.SmallFurniture;
import ru.arrowin.bedstoremanager.services.SmallFurnitureService;

import java.util.ArrayList;
import java.util.List;
/***
 * Клавиатура вывода всей малой мебели на предприятии для отметки "Сделано"
 */
@Component
public class SmallFurnitureKeyBoard implements KeyBoard {
    @Value(value = "${symbol.for.split}") private String SPLIT;
    private final SmallFurnitureService smallFurnitureService;

    public SmallFurnitureKeyBoard(SmallFurnitureService smallFurnitureService) {
        this.smallFurnitureService = smallFurnitureService;
    }


    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        List<SmallFurniture> smallFurnitures = smallFurnitureService.readAll();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (SmallFurniture smallFurniture : smallFurnitures) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(smallFurniture.getName());
            button.setCallbackData(CommandName.ADD_CREATED_SMALL_FURNITURE.getCommandName()+"&&"+smallFurniture.getId());
            keyboard.add(createButtonsLine(button));
        }
        return new InlineKeyboardMarkup(keyboard);
    }
}
