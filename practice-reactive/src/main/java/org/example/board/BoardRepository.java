package org.example.board;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BoardRepository {

    public static Map<Long, Board> boardMap = new ConcurrentHashMap<>();

    public static Board getBoard(Long id) {
        return boardMap.get(id);
    }

    public static Board addBoard(Board board) {
        return boardMap.put((long) (boardMap.size()+1), board);
    }

    public static List<Board> getBoardList() {
        return boardMap.entrySet().stream().map(Map.Entry::getValue).toList();
    }
}
