package org.example.support.handler.user;

import lombok.Getter;

@Getter
public class UserEvent {

    private String email;

    public UserEvent(String email) {
        this.email = email;
    }
}
