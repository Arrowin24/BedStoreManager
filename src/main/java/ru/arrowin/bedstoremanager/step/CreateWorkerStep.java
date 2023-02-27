package ru.arrowin.bedstoremanager.step;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.arrowin.bedstoremanager.keyboard.PositionsKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.StartKeyBoard;
import ru.arrowin.bedstoremanager.keyboard.WorkerKeyBoard;
import ru.arrowin.bedstoremanager.models.Position;
import ru.arrowin.bedstoremanager.models.Worker;
import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;

@Log4j
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class CreateWorkerStep extends Step {
    private final Worker worker;

    private final WorkerService workerService;

    private final static String START_TEXT = "Введите имя: ";
    private final static String ADD_NAME_TEXT = "Введите пароль: ";
    private final static String ADD_PASSWORD_TEXT = "Введите должность: ";
    private final static String ADD_POSITION_TEXT = "Введите пароль для подтверждения своей должности: ";
    private final static String CHECK_POSITION_PASSWORD_TEXT = "Пароль принят!";
    private final static String FINISH_TEXT = "Регистрация прошла успешно: ";
    private final static String ERROR_TEXT = "Произошла ошибка. Пожалуйста, попробуйте повторить регистрацию.";

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
            case ONE -> addNameStep(update);
            case TWO -> addPasswordStep(update);
            case THREE -> addPositionStep(update);
            case FOUR -> checkPositionPasswordStep(update);
            case FIVE -> finishStep(update);
            default -> {
                errorStep(update);
                log.debug("Произошла ошибка регистрации. У пользователя с id="
                                  + update.getMessage().getChatId() + " последнее сообщение: "
                                  + update.getMessage().getText());
            }
        }
    }

    @Override
    public void startStep(Update update) {
        long id = getId(update);
        CreateWorkerStep workerStep = new CreateWorkerStep(getContainer(), workerService, getSendBotMessageService());
        workerStep.setStep(StepName.ONE);
        workerStep.getWorker().setId(id);
        getContainer().putStep(id, workerStep);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(START_TEXT);
        getSendBotMessageService().sendMessage(message);
    }


    private void addNameStep(Update update) {
        long id = getId(update);
        String name = update.getMessage().getText();
        worker.setName(name);
        setStep(StepName.TWO);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ADD_NAME_TEXT);
        getSendBotMessageService().sendMessage(message);
    }

    private void addPasswordStep(Update update) {
        long id = getId(update);
        String password = update.getMessage().getText();
        worker.setPassword(password);
        setStep(StepName.THREE);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ADD_PASSWORD_TEXT);
        message.setReplyMarkup(new PositionsKeyBoard().getKeyBoard());
        getSendBotMessageService().sendMessage(message);
    }

    private void addPositionStep(Update update) {
        long id = getId(update);
        Position position = Position.valueOf(update.getCallbackQuery().getData().trim().toUpperCase());
        worker.setPosition(position);
        setStep(StepName.FOUR);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ADD_POSITION_TEXT);
        getSendBotMessageService().sendMessage(message);
    }

    private void checkPositionPasswordStep(Update update) {
        String password = update.getMessage().getText().trim();
        if (!password.equals(worker.getPosition().getPassword())) {
            setStep(StepName.ERROR);
            doStep(update);
            return;
        }
        long id = getId(update);
        setStep(StepName.FIVE);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(CHECK_POSITION_PASSWORD_TEXT);
        getSendBotMessageService().sendMessage(message);
        doStep(update);
    }

    public void errorStep(Update update) {
        setStep(StepName.FOUR);
        long id = getId(update);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(ERROR_TEXT);
        message.setReplyMarkup(new StartKeyBoard().getKeyBoard());
        getSendBotMessageService().sendMessage(message);
    }


    public void finishStep(Update update) {
        long id = getId(update);
        workerService.create(worker);
        getContainer().deleteStep(id);
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(FINISH_TEXT + "\n" + workerService.read(id).toText());
        message.setReplyMarkup(new WorkerKeyBoard().getKeyBoard());
        getSendBotMessageService().sendMessage(message);
    }


}
