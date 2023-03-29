package ru.arrowin.bedstoremanager.keyboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.arrowin.bedstoremanager.models.Bed;
import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreatedTodayBedsKeyBoard  implements KeyBoard{

    @Value(value = "${symbol.for.split}") private String SPLIT;
    private final CreatedBedsService createdBedsService;

    public CreatedTodayBedsKeyBoard(CreatedBedsService createdBedsService) {
        this.createdBedsService = createdBedsService;
    }

    @Override
    public InlineKeyboardMarkup getKeyBoard() {
        LocalDate today =LocalDate.now();

        List<CreatedBed> beds = createdBedsService.readAll();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

    }
}
