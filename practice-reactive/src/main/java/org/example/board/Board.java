package org.example.board;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Board {
    private Long id;
    private String title;
    private String contents;

    @Builder
    public Board(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
}
