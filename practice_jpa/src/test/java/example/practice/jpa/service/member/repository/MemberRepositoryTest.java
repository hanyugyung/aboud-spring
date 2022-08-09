package example.practice.jpa.service.member.repository;

import example.practice.jpa.service.member.entity.Bookmark;
import example.practice.jpa.service.member.entity.Member;
import example.practice.jpa.service.member.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    private Team createTeam(String name) {
        return teamRepository.save(Team.of(name));
    }

    private Member createMember(String name, Team team) {
        return memberRepository.save(Member.of(name, team));
    }

    @BeforeEach
    public void setup() {

    }

    // 영속성 사용 위해 트랜잭션 범위 잡아줌
    @Test
    @Transactional
    void 다대일의_관계에서_다_쪽에서의_n_plus_1_문제() {

        // 1 + n 만큼 쿼리 발생
        // fetch lazy 나 eager 와는 관계 없이 발생할 수 있는 문제

        // given
        Team team1 = createTeam("Team1");
        Team team2 = createTeam("Team2");
        Team team3 = createTeam("Team3");
        createMember("member1", team1);
        createMember("member2", team2);
        createMember("member3", team3);

        entityManager.flush();
        entityManager.clear();

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertNotNull(memberList.get(0).getTeam().getName());
        assertNotNull(memberList.get(1).getTeam().getName());
        assertNotNull(memberList.get(2).getTeam().getName());

        // list 1번 조회 쿼리, 각 member 가 속한 team 을 가져오는 쿼리가 member list 크기만큼 실행
        // 최악의 상황 -> list 의 모든 member 가 각각 다른 team 에 속할 때
    }

    @Test
    @Transactional
    void 다대일의_관계에서_일_쪽에서의_n_plus_1_문제() {

        // 1 + n 만큼 쿼리 발생
        // fetch lazy 나 eager 와는 관계 없이 발생할 수 있는 문제
        // 단, eager 로딩은 한번에 쿼리가 실행되기 때문에 리스크가 더 크고,
        // lazy 로딩은 참조하지 않는 이상은 추가 쿼리가 일어나지 않지만 잠재적인 리스크 존재한다고 볼 수 있음

        // given
        Team team1 = createTeam("Team1");
        Team team2 = createTeam("Team2");

        createMember("member1", team1);
        createMember("member2", team1);
        createMember("member3", team2);

        entityManager.flush();
        entityManager.clear();

        // when
        List<Team> teamList = teamRepository.findAll();

        // then
        assertNotNull(teamList.get(0).getMemberList().get(0));
        assertNotNull(teamList.get(1).getMemberList().get(0));

        // list 1번 조회 쿼리, 각 team 에 속한 member list 를 가져오는 쿼리가 team list 크기만큼 실행
    }

    @Test
    @Transactional
    void 다대일의_관계에서_n_plus_1_문제_해결_join_fetch() {

        // given
        Team team1 = createTeam("Team1");
        Team team2 = createTeam("Team2");

        createMember("member1", team1);
        createMember("member2", team1);
        createMember("member3", team2);

        entityManager.flush();
        entityManager.clear();

        // when
        List<Member> memberList = memberRepository.findAllUsingJoinFetch();

        // then
        assertNotNull(memberList.get(0).getTeam().getName());
        assertNotNull(memberList.get(1).getTeam().getName());

        entityManager.clear();

        // when
        List<Team> teamList = teamRepository.findAllUsingJoinFetch();

        // then
        assertNotNull(teamList.get(0).getMemberList().get(0));
        assertNotNull(teamList.get(1).getMemberList().get(0));

        // join fetch 로 쿼리 한번에 가져옴. lazy 로딩보다 join fetch 가 우선순위
        // join fetch 는 기본이 inner join 발생
    }

    @Test
    @Transactional
    void 다대일의_관계에서_n_plus_1_문제_해결_EntityGraph() {

        // given
        Team team1 = createTeam("Team1");
        Team team2 = createTeam("Team2");

        createMember("member1", team1);
        createMember("member2", team1);
        createMember("member3", team2);

        entityManager.flush();
        entityManager.clear();

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertNotNull(memberList.get(0).getTeam().getName());
        assertNotNull(memberList.get(1).getTeam().getName());

        // entity graph 로 쿼리 한번에 가져옴.
        // left outer join 발생
    }

    @Test
    void 다대일의_관계에서_n_plus_1_문제_해결_querydsl() {
        // TODO querydsl 로 n+1 문제 해결 테스트 코드 작성
    }

    @Test
    void 주의_다대일_일쪽에서_list_조회시_같은_엔티티_여러개_발생하는경우_distinct_로해결() {

        // given
        Team team1 = createTeam("Team1");
        Team team2 = createTeam("Team2");

        createMember("member1", team1);
        createMember("member2", team1);
        createMember("member3", team2);

        // when
        List<Team> teamList = teamRepository.findAllUsingJoinFetch();

        // then
        assertEquals(3, teamList.size()); // team 2개인데, list 사이즈는 3(조인되면서 발생)

        // when
        List<Team> teamList_Distinct = teamRepository.findAllUsingJoinFetchDistinct();

        // then
        assertEquals(2, teamList_Distinct.size());
    }

    @Test
    void cascade_remove_테스트_부모객체_삭제시_자식객체도_삭제() {

        // given
        Team team = createTeam("team1");
        Member member = createMember("han60", team);
        member.addMyBookmark(new Bookmark("Tile1", "Content1"));
        member.addMyBookmark(new Bookmark("Tile2", "Content2"));

        // when
//        Optional<Member> optionalMember = memberRepository.findById(member.getId());
//        assertEquals(2, optionalMember.get().getBookmarkList().size());
//
//        memberRepository.delete(optionalMember.get());

        memberRepository.delete(member); // delete 하기 전에 flush

        // then
        assertEquals(0, bookmarkRepository.findAll().size());

        // 부모객체(member) 자체를 삭제하기 때문에 cascade remove 속성으로 자식객체 삭제,
        // 고아객체 true 속성과 관계없음
    }

    @Test
    void 부모객체와_연관관계_끊기면_고아객체_삭제_테스트() {

        // given
        Team team = createTeam("team1");
        Member member = Member.of("han60", team);
        member.addMyBookmark(new Bookmark("Tile1", "Content1"));
        member.addMyBookmark(new Bookmark("Tile2", "Content2"));

        memberRepository.save(member);

        // when
        member.deleteMyAllBookmarks();

        // then
        memberRepository.findById(member.getId());
        assertEquals(0, member.getBookmarkList().size());

    }

}