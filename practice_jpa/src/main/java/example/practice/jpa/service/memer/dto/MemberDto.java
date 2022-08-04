package example.practice.jpa.service.memer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.List;

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
}
