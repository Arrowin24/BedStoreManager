package ru.arrowin.bedstoremanager.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandName {
    START("/start"),
    HELP("/help"),
    CREATE_WORKER("/newWorker"),
    LOGIN("/login"),
    CREATE_FURNITURE("/newFurniture"),
    ADD_FURNITURE("/addFurniture"),
    GET_FURNITURE_TODAY("/getFurnitureToday"),
    GET_SALARY_TODAY("/getSalaryToday"),
    GET_ALL_WORKERS("/getAllWorkers"),
    UNKNOWN("/unknown"),
    GET_ALL_WORK_TODAY("/getAllWorkToday"),
    GET_ALL_BEDS("/getAllBeds"),
    GET_ALL_SMALL_FURNITURE("/getAllSmallFurniture"),
    GET_ALL_OTHER_WORK("/getAllOtherWork"),
    ADD_CREATED_BED("/addCreatedBed"),
    ADD_CREATED_OTHER_WORK("/addCreatedOtherWork"),
    ADD_CREATED_SMALL_FURNITURE("/addCreatedSmallFurniture");


    private final String commandName;
}
