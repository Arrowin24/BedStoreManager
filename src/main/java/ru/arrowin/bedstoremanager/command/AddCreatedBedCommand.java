package ru.arrowin.bedstoremanager.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.models.CreatedBed;
import ru.arrowin.bedstoremanager.services.CreatedBedsService;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;

import java.time.LocalDate;

@Component
public class AddCreatedBedCommand extends Command {

    @Value("${symbol.for.split}") private String SPLIT;
    private final static String PREVIEW = "Вы сделали кровать ";
    private final SendBotMessageService sendBotMessageService;
    private final CreatedBedsService createdBedsService;

    public AddCreatedBedCommand(SendBotMessageService sendBotMessageService, CreatedBedsService createdBedsService) {
        super(CommandName.ADD_CREATED_BED);
        this.sendBotMessageService = sendBotMessageService;
        this.createdBedsService = createdBedsService;
    }

    @Override
    public void execute(Update update) {
        Long userId = getId(update);
        int bedId =Integer.parseInt(update.getCallbackQuery().getData().split(SPLIT)[1]);
        LocalDate today = LocalDate.now();
        CreatedBed bed = new CreatedBed(userId,bedId,today);
        createdBedsService.add(bed);
        SendMessage message = new SendMessage();
        message.setChatId(getId(update));
        message.setText(PREVIEW + createdBedsService.getTodayCreatedBeds(userId) +"\n"+"Сегодня вы заработали на кроватях: "+createdBedsService.getTodayBedSalary(userId));
        sendBotMessageService.sendMessage(message);
    }
}
