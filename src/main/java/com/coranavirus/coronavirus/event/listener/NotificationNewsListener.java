package com.coranavirus.coronavirus.event.listener;

import com.coranavirus.coronavirus.entity.Users;
import com.coranavirus.coronavirus.event.OnNotificationNewsEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationNewsListener implements ApplicationListener<OnNotificationNewsEvent> {
    private Users user;
    private
}
