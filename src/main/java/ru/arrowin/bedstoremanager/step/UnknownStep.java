package ru.arrowin.bedstoremanager.step;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UnknownStep extends Step {
    @Override
    public SendMessage doStep(StepName step) {

        return null;
    }
}
