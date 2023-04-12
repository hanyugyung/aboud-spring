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
* 제가 이해한 레이어드, 클린, 헥사고날 아키텍처의 차이입니다.(틀리거나 보완할 점을 알게 되면 수정하겠습니다.)
    * 클린 아키텍처와 헥사고날 아키텍처, 레이어드 아키텍처를 가르는 핵심은 인터페이스를 어느 계층에 두느냐? 입니다.
    * 레이어드 아키텍처는 Presentaion / Application / Persistence 패키지 내에 각 기능(구현체)와 해당 기능의 인터페이스가 같은 계층에 위치합니다. 즉, 의존도가 Presentation -> Application -> Persistence 방향으로 흐르게 되어, 하위 계층이 변경되게 되면 상위 계층도 같이 변경될 확률이 높습니다. 또한 도메인이 아닌 각 역할에 따라 패키지를 구성하게 되기 때문에 도메인이 변경되는 경우 각 계층 별로 변경해야 하기 때문에 변경해야할 코드량이 많아집니다.(상위가 하위 계층에 의존도가 높음)
    * 클린 아키텍처는 각 도메인 별 Presentation / (Facade) / Application(UseCase) / Persistence 패키지를 가지고 있고, 패키지 구조는 레이어드 아키텍처와 비슷한 듯 하지만, Application 에 Persistence 가 구현해야할 인터페이스를 두어 Application -> Persistence 로의 의존방향이 아닌, Application <- Persistence 의 의존 관계로 역전이 되게 됩니다.(DIP) 그렇게 되면, Persistence 에서의 구현체가 변경되어도 그 위의 계층인 Application 는 변경될 필요가 없어집니다.
    * 헥사고날 아키텍처에서는 Adapter / Application / Domain 패키지로 구성되고, Adapter 패키지에는 ApiController, Event Handler, DB 등 변하기 쉬운 외부 영역의 기능이 있습니다. ApiController 는 In-Adapter, DB, EventHandler 등은 Out-Adapter 로 나뉘어 집니다. Application 패키지는 내부 주요 비즈니스로직과 Application > Port 라고하는 패키지 하위에 이 Adapter 들의 인터페이스를 둡니다. 이렇게 되면, 변하기 쉬운 외부 영역이 변경되어야할 때 Adapter 에서의 구현체만 변경하면 되고, Application 내부에는 영향을 주지 않게 됩니다.
-------------------------

#### 레디스 분산락을 이용한 동시성처리
> 레디스 클라이언트인 Redisson 을 이용한 동시성처리 간단한 예제 작성

* 분산 서버 환경에서 한 자원을 동시에 여러 서버에서 접근할 경우, 데이터 정합성을 지키기 위한 방법
* Redisson 에서 제공하는 tryLock 함수를 통해 자원 점유를 하기 위해 대기하고, 자원 점유 시간을 지정할 수 있습니다.

-------------------------
#### 스프링부트 웹소켓 기본 예제


-------------------------
#### Java Tus 업로드
> 자바 Tus 업로드 예제 작성

* Tus 프로토콜이란? 
   * 재개 가능한 업로드를 위해 개발된 HTTP 기반 오픈 프로토콜
   * 파일을 청크 단위로 잘라서 업로드하기 때문에 대용량 파일 업로드에 적합
   
-------------------------
#### 스프링부트 Graphql
> 스프링부트 Graphql 예제 작성

* Rest API 와의 차이
   * Rest API 는 http url 로 자원을 명시하고, method 로 CRUD 오퍼레이션을 적용하는 반면, /graphql 라는 엔드포인트 하나만 존재함.
      * Query(조회), Mutation(생성, 수정, 삭제) 로 구분된 쿼리만 존재
   * Rest API 의 over-fetching, under-fetching 문제 해결
      * over-fetching: 필요 이상의 자원을 가져오는 것 / under-fetching: 필요한 자원을 충분히 가져오지 못하는 것
