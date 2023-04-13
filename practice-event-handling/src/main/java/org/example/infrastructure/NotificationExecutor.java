package org.example.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.notification.NotificationService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationExecutor implements NotificationService {

    @Override
    public void sendEmail(String email, String title, String description) {
        log.info("sendEmail");
    }

    @Override
    public void sendKakao(String phoneNo, String description) {
        log.info("sendKakao");
    }

    @Override
    public void sendSms(String phoneNo, String description) {
        log.info("sendSms");
    }
}