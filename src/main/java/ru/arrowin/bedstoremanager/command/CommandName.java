package ru.arrowin.bedstoremanager.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandName {
    START("/start"),
    HELP("/help"),
    CREATE_WORKER("/newWorker"),
    CREATE_FURNITURE("/newFurniture")
    ;
    private final String commandName;
}
