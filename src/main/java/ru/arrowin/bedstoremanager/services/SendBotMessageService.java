package ru.arrowin.bedstoremanager.services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public interface SendBotMessageService {
    void sendMessage(SendMessage message);
}
