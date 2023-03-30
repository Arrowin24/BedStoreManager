package ru.arrowin.bedstoremanager.models;
//Перечисление существующих должностей
public enum Position {
    WORKER("123"),
    ADMIN("adminer"),
    CHEF_ENGINEER("250223");

    private final String password;
    Position(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
