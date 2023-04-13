package org.example.support.handler.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.notification.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;

    @EventListener
    @Async
    public void alarm(UserEvent userEvent) {
        Thread thread = Thread.currentThread();
        notificationService.sendEmail(userEvent.getEmail(), "가입 축하", "가입을 축하드립니다.");
        log.info("alarm 함수 실행 스레드 이름 : {}", thread.getName());
    }

}
