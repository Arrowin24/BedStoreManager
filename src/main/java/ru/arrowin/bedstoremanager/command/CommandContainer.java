package ru.arrowin.bedstoremanager.command;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(List<Command> commands, UnknownCommand unknownCommand) {
        commandMap = commands.stream()
                             .filter(command -> !command.getName().equals(CommandName.UNKNOWN))
                             .collect(
                Collectors.toUnmodifiableMap(command -> command.getName().getCommandName(), command -> command,
                                             (o1, o2) -> o1));

        this.unknownCommand = unknownCommand;
    }

    public CommandBehavior retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
