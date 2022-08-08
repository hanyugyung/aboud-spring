package example.practice.jpa.service.member;

import example.practice.jpa.service.member.dto.MemberDto;
import example.practice.jpa.service.member.entity.Bookmark;
import example.practice.jpa.service.member.entity.Member;
import example.practice.jpa.service.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Member Not Founded!"));
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberInfo getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member Not Founded!"));

        // FIXME mapstruct 로 변환
        return MemberDto.MemberInfo.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .name(member.getName())
                .teamName(member.getTeam().getName())
                .build();

    }

    @Transactional
    public void createMemberBookmark(Long memberId, MemberDto.BookmarkCommand command){
        Bookmark bookmark = command.toEntity();
        Member member = getMember(memberId);
        member.addMyBookmark(bookmark);
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberBookmarkInfo getMemberBookmarks(Long memberId){
        Member member = getMember(memberId);
        return MemberDto.MemberBookmarkInfo.of(member);
    }
}
