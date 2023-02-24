package ru.arrowin.bedstoremanager.step;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.arrowin.bedstoremanager.keyboard.WorkerKeyBoard;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;



@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class CreateWorkerStep extends Step {
    private final Worker worker;

    private final WorkerService workerService;

    private final static String START_TEXT = "Введите имя: ";
    private final static String ADD_NAME_TEXT = "Введите пароль: ";
    private final static String ADD_PASSWORD_TEXT = "Введите должность: ";
    private final static String ADD_POSITION_TEXT = "Позиция принята: ";
    private final static String FINISH_TEXT = "Регистрация прошла успешно: ";


    public CreateWorkerStep(
            StepsContainer container, WorkerService workerService, SendBotMessageService sendBotMessageServices)
    {
        super(container, sendBotMessageServices);
        this.workerService = workerService;
        this.worker = new Worker();
    }

    public Worker getWorker() {
        return worker;
    }

    @Override
    public void doStep(Update update) {
        Step currentStep = getContainer().getStep(update);
        switch (currentStep.getStep()) {
            case ONE -> addWorkerNameStep(update);
            case TWO -> addWorkerPasswordStep(update);
            case THREE -> {
                addWorkerPositionStep(update);
                finishStep(update);

            }
            default -> throw new RuntimeException("Что то не так");
        }
    }

    @Override
    public void startStep(Update update) {
        long id = getId(update);
        CreateWorkerStep workerStep = new CreateWorkerStep(getContainer(),workerService, getSendBotMessageService());
        workerStep.setStep(StepName.ONE);
        workerStep.getWorker().setId(id);
        getContainer().putStep(id, workerStep);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(START_TEXT);
        getSendBotMessageService().sendMessage(message);
    }


    private void addWorkerNameStep(Update update) {
        long id = getId(update);
        String name = update.getMessage().getText();
        worker.setName(name);
        setStep(StepName.TWO);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ADD_NAME_TEXT);
        getSendBotMessageService().sendMessage(message);
    }

    private void addWorkerPasswordStep(Update update) {
        long id = getId(update);
        String password = update.getMessage().getText();
        worker.setPassword(password);
        setStep(StepName.THREE);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ADD_PASSWORD_TEXT);
        getSendBotMessageService().sendMessage(message);
    }

    private void addWorkerPositionStep(Update update) {
        long id = getId(update);
        String position = update.getMessage().getText();
        worker.setPosition(position);
        setStep(StepName.FOUR);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ADD_POSITION_TEXT);
        getSendBotMessageService().sendMessage(message);
    }

    public void finishStep(Update update) {
        long id = getId(update);
        workerService.create(worker);
        getContainer().deleteStep(id);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(FINISH_TEXT + "\n" + workerService.read(id));
        message.setReplyMarkup(new WorkerKeyBoard().getKeyBoard());
        getSendBotMessageService().sendMessage(message);


    }


}
