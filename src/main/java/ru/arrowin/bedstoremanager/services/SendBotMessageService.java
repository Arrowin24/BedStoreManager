package ru.arrowin.bedstoremanager.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public interface SendBotMessageService {
    void sendMessage(SendMessage message);
}
