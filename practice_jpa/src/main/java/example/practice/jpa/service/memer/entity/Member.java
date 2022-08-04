package example.practice.jpa.service.memer.entity;

import example.practice.jpa.service.memer.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Member(String name, Team team) {
        if(!StringUtils.hasText(name)) throw new IllegalArgumentException();
        this.name = name;
        this.team = team;
    }

    public static Member of(String name, Team team) {
        return Member.builder().name(name).team(team).build();
    }

    public void setTeam(Team team){
        this.team = team; // 이 코드 필수는 아님
    }
}
