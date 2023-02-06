package ru.arrowin.bedstoremanager.step;

import lombok.Getter;

@Getter
public enum StepName {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4)
    ;

    private final int stepNum;

    StepName(int stepNum) {
        this.stepNum = stepNum;
    }
}
