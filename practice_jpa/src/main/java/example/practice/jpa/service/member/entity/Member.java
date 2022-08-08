package example.practice.jpa.service.member.entity;

import example.practice.jpa.service.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarkList = List.of();

    @Builder
    public Member(String name, Team team) {
        if(!StringUtils.hasText(name)) throw new IllegalArgumentException();
        this.name = name;
        this.team = team;
    }

    public static Member of(String name, Team team) {
        return Member.builder().name(name).team(team).build();
    }

    public void addMyBookmark(Bookmark bookmark) {
        this.bookmarkList.add(bookmark);
        bookmark.setMember(this);
    }

    public Bookmark findMyBookmark(Long bookmarkId) {
        return this.bookmarkList.stream()
                .filter(bookmark -> bookmark.getId().equals(bookmarkId))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Not Founded Bookmark"));
    }

    public void deleteMyBookmark(Long bookmarkId) {
        this.bookmarkList.stream()
                .filter(bookmark -> bookmark.getId().equals(bookmarkId))
                .findFirst().ifPresent(bookmark -> {
                    this.bookmarkList.remove(bookmark);
                });
    }

    public void deleteMyAllBookmark() {
        this.bookmarkList.clear();
    }
}
