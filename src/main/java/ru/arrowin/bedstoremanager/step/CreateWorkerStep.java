package ru.arrowin.bedstoremanager.step;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CreateWorkerStep extends Step{


    @Override
    public SendMessage doStep(StepName step) {
        SendMessage message = new SendMessage();
        return null;
    }
}
