package example.practice.jpa.service.member.entity;

import example.practice.jpa.service.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Builder
    public Member(String name, Team team) {
        if (!StringUtils.hasText(name)) throw new IllegalArgumentException();
        this.name = name;
        this.team = team;
    }

    public static Member of(String name, Team team) {
        return Member.builder().name(name).team(team).build();
    }

    public void editName(String name) {
        this.name = name;
    }

    public void addMyBookmark(Bookmark bookmark) {
        this.bookmarkList.add(bookmark);
        bookmark.setMember(this); // 이 코드는 편의상 추가한 것, 영속성과 상관없음
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

    public void deleteMyAllBookmarks() {
        this.bookmarkList.clear();
    }
}
