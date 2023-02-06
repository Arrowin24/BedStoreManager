package ru.arrowin.bedstoremanager.step;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class Step {
    private StepName step;


    public abstract SendMessage doStep(StepName step);
}
