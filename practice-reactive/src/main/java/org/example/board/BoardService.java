package org.example.board;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoardService {
    Mono<Board> get(Long id);

    Flux<Board> getList();
}
