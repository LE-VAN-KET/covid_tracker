package com.coranavirus.coronavirus.event;

import com.coranavirus.coronavirus.entity.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnNotificationNewsEvent extends ApplicationEvent {
    private Users user;
    private String content;

    public OnNotificationNewsEvent(Users user, String content) {
        super(user);
        this.user = user;
        this.content = content;
    }
}
