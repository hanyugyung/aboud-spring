# spring-study
-------------------------
#### 주요 개발 환경
  * java 17
  * Spring Boot 2.7
  * Gradle 7.4

-------------------------

#### Jpa
> Team, Member, Bookmark 세 엔티티의 Team-Member(1:n), Member-Bookmark(1:n) 연관관계를 예시로(예제의 엔티티가 억지스러울 수 있지만..), 실무에서 겪었던 jpa 사용으로 인한 문제 상황/해결이나 헷갈리고 어려웠던 내용을 테스트코드로 정리하고자 합니다.

* jpa 에서 n+1 문제가 일어나는 이유/방지
* cascade remove vs orphanremoval (Aggregate Root 로서의 작용)
* dirty checking 변경 감지
* lazy initialization exception - no session
* native query 로 select 하는 경우도 영속화가 되는지?

-------------------------

#### Clean / Hexagonal Architecture
> 클린 아키텍처와 헥사고날 아키텍처에 대해 공부하고, 어떤 점이 다른지 등 공부한 내용을 샘플 코드로 구현해보고자 합니다.

-------------------------

#### 레디스 분산락을 이용한 동시성처리
> 레디스 클라이언트인 Redisson 을 이용한 동시성처리 간단한 예제 작성

* 분산 서버 환경에서 한 자원을 동시에 여러 서버에서 접근할 경우, 데이터 정합성을 지키기 위한 방법
* Redisson 에서 제공하는 tryLock 함수를 통해 자원 점유를 하기 위해 대기하고, 자원 점유 시간을 지정할 수 있습니다.

-------------------------
#### 스프링부트 웹소켓 기본 예제
> Stomp, SockJs 사용하지 않은 기본 웹소켓 예제 작성
