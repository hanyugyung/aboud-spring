package org.example.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.support.handler.user.UserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String email) {

        // 1. User 영속화
        // 생략
        // 2. User 에게 가입 안내 메일 발송
        publisher.publishEvent(new UserEvent(email));

        for (int i = 0; i < 10; i++) {
            log.info("메인스레드");
        }
    }
}
