package ru.arrowin.bedstoremanager.command;

import ru.arrowin.bedstoremanager.services.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, Command> commandMap = new HashMap<>();
  //  private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap.put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService));

    }

   /* public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault();
    }*/
}
