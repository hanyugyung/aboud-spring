package org.example.board;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BoardServiceImpl implements BoardService {
    @Override
    public Mono<Board> get(Long id) {
        return Mono.just(BoardRepository.getBoard(id));
    }

    @Override
    public Flux<Board> getList() {
        return Flux.fromIterable(BoardRepository.getBoardList());
    }
}
