package ru.arrowin.bedstoremanager.command;


/**
* Абстрактный класс для дальнейшей реализации интерфейся CommandBehavior
* */
public abstract class Command implements CommandBehavior {

    //Названиие команды выбирается из ENUM CommandName
    private final CommandName name;

    public Command(CommandName name) {
        this.name = name;
    }

    public CommandName getName() {
        return name;
    }
}
