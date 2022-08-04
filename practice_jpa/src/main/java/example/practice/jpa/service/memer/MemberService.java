package example.practice.jpa.service.memer;

import example.practice.jpa.service.memer.dto.MemberDto;
import example.practice.jpa.service.memer.entity.Member;
import example.practice.jpa.service.memer.entity.Team;
import example.practice.jpa.service.memer.repository.MemberRepository;
import example.practice.jpa.service.memer.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final TeamRepository teamRepository;

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member Not Founded!"));
    }

    @Transactional(readOnly = true)
    public MemberDto.MemberInfo getMemberInfo(Long id) {
        Member member = memberRepository.findById(id)
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

}
