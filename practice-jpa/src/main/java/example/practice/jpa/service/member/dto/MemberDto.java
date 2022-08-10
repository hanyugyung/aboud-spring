package example.practice.jpa.service.member.dto;

import example.practice.jpa.service.member.entity.Bookmark;
import example.practice.jpa.service.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberDto {

    @Getter
    @ToString
    @Builder
    public static class MemberInfo {
        private Long id;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private String name;
        private String teamName;
    }

    @Getter
    @ToString
    @Builder
    public static class TeamInfo {
        private Long id;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private List<MemberInfo> memberName;
        private String teamName;
    }

    @Getter
    @ToString
    @Builder
    public static class BookmarkCommand {
        private String title;
        private String content;

        public Bookmark toEntity() {
            Objects.requireNonNull(this.title, "title required!");
            return new Bookmark(this.title, this.content);
        }
    }

    @Getter
    @ToString
    @Builder
    public static class BookmarkInfo {
        private Long id;
        private String title;
        private String content;
    }

    @Getter
    @ToString
    @Builder
    public static class MemberBookmarkInfo {
        private Long id;
        private String name;
        private List<BookmarkInfo> bookmarkList;

        public static MemberBookmarkInfo of(Member member){
            return new MemberBookmarkInfo(
                    member.getId()
                    , member.getName()
                    , member.getBookmarkList().stream().map(
                            b -> new BookmarkInfo(b.getId(), b.getTitle(), b.getContent()))
                    .collect(Collectors.toList())
            );
        }
    }
}
