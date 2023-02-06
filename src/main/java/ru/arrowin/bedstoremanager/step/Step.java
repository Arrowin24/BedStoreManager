package ru.arrowin.bedstoremanager.step;

import ru.arrowin.bedstoremanager.services.SendBotMessageService;

public abstract class Step implements StepBehavior {
    private StepName step;

    private final StepsContainer container;
    private final SendBotMessageService sendBotMessageService;

    public Step(StepsContainer container, SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
        this.container = container;
    }

    public StepsContainer getContainer() {
        return container;
    }

    public SendBotMessageService getSendBotMessageService() {
        return sendBotMessageService;
    }

    public StepName getStep() {
        return step;
    }

    public void setStep(StepName step) {
        this.step = step;
    }
}
