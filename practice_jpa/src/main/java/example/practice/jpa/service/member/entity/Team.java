package example.practice.jpa.service.member.entity;

import example.practice.jpa.service.BaseEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@NoArgsConstructor
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> memberList = List.of();

    @Builder
    public Team(String name) {
        if(!StringUtils.hasText(name)) throw new IllegalArgumentException();
        this.name = name;
    }

    public static Team of(String name) {
        return Team.builder().name(name).build();
    }

    public void addMember(Member member) {
        this.memberList.add(member);
    }
}
