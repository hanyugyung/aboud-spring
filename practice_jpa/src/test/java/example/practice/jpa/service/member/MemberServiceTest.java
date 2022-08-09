package example.practice.jpa.service.member;

import example.practice.jpa.service.member.dto.MemberDto;
import example.practice.jpa.service.member.entity.Member;
import example.practice.jpa.service.member.entity.Team;
import example.practice.jpa.service.member.repository.MemberRepository;
import example.practice.jpa.service.member.repository.TeamRepository;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Team createTeam(String name) {
        return teamRepository.save(Team.of(name));
    }

    private Member createMember(String name, Team team) {
        return memberRepository.save(Member.of(name, team));
    }

    @BeforeEach
    public void setup() {

    }

    @Test
    void dto_사용으로_Lazy_Initialization_exception_방지() {

        // given
        Team team = createTeam("team1");
        Member member = createMember("han60", team);
        Long memberId = member.getId();

        // when, then
        assertEquals(memberService.getMemberInfo(memberId).getTeamName(), team.getName());

        // dto 사용하여 LazyInitializationException 방지
    }

    @Test
    void lazy_Initialization_Exception_발생_테스트() {

        // given
        Team team = createTeam("team1");
        Member member = createMember("han60", team);
        Long memberId = member.getId();

        // when
        Member getMember = memberService.getMember(memberId);

        // then
        assertFalse(Hibernate.isInitialized(memberService.getMember(memberId).getTeam()));
        assertThrows(LazyInitializationException.class
                , () -> memberService.getMember(memberId).getTeam().getName());

        // 트랜잭션 범위 밖에서 프록시 Team 객체의 값 Name 참조하려고 했기 때문에 발생
    }

    @Test
    void bookmark_생성_테스트() {

        // given
        Team team = createTeam("team1");
        Member member = createMember("han60", team);

        // when
        memberService.createMemberBookmark(member.getId()
                , MemberDto.BookmarkCommand.builder().title("title").content("content").build());

        // then
        assertEquals(1
                , memberService.getMemberBookmarks(member.getId()).getBookmarkList().size());
    }
}