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


