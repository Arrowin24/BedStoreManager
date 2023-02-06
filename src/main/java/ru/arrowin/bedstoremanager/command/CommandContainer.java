package ru.arrowin.bedstoremanager.command;

import ru.arrowin.bedstoremanager.services.SendBotMessageService;
import ru.arrowin.bedstoremanager.services.WorkerService;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private final Map<String, Command> commandMap = new HashMap<>();
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, WorkerService workerService) {

        commandMap.put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(CommandName.CREATE_WORKER.getCommandName(),
                       new CreateWorkerCommand(sendBotMessageService, workerService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
