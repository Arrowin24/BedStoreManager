package ru.arrowin.bedstoremanager.step;

import lombok.Getter;

@Getter
public enum StepName {
    ZERO(0),
    ONE(1)
    ;

    private final int stepNum;

    StepName(int stepNum) {
        this.stepNum = stepNum;
    }
}
