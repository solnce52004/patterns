package com.example.patterns.behavioral.mediator.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

public class MediatorMain {
    public static void main(String[] args) {
        SimpleTextChat chat = new SimpleTextChat();

        //регистрируем чат в пользователе, но делегируем отправку чату
        User admin = new Admin(chat, "Admin123");
        User user1 = new SimpleUser(chat, "User 10");
        User user2 = new SimpleUser(chat, "User 20");

        //регистрируем пользователей в чате
        //Конкретные компоненты никак не связаны между собой.
        //У них есть только один канал общения – через отправку уведомлений посреднику.
        chat.setAdmin(admin);
        chat.addUserToChat(user1);
        chat.addUserToChat(user2);

        //вызов у пользователя - обертка (все равно делегировали отправку чату)
        user1.sendMessage("I am User 10!!!");
        admin.sendMessage("I am admin123!!!");
    }
}

interface Chat {
    void send(String message, User user);
}

@Setter
class SimpleTextChat implements Chat {
    User admin;
    List<User> users = new ArrayList<>();

    public void addUserToChat(User user) {
        this.users.add(user);
    }

    @Override
    public void send(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.getMessage(message);
            }
        }
        admin.getMessage(message);
    }
}

interface User {
    void sendMessage(String message);
    void getMessage(String message);
}

@Log4j2
@AllArgsConstructor
@Getter
@Setter
class Admin implements User {
    private final Chat chat;
    private final String name;

    @Override
    public void sendMessage(String message) {
        getChat().send(message, this);//регистрируем чат в админе, но делегируем чату отправку
    }

    @Override
    public void getMessage(String message) {
        log.info( "Admin {} receiving message: {}", getName(),  message);
    }
}

@Log4j2
@AllArgsConstructor
@Getter
@Setter
class SimpleUser implements User {
    private final Chat chat;
    private final String name;

    @Override
    public void sendMessage(String message) {
        getChat().send(message, this);
    }

    @Override
    public void getMessage(String message) {
        log.info( "User {} receiving message: {}.", getName(),  message);
    }
}
