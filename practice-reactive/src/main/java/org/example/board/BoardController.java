package org.example.board;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    @PostConstruct
    public void initMap() {
        BoardRepository.addBoard(new Board(1l, "title", "contents"));
        BoardRepository.addBoard(new Board(2l, "title2", "contents3"));
        BoardRepository.addBoard(new Board(3l, "title2", "contents3"));
    }

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Board>> get(@PathVariable Long id) {
        return new ResponseEntity<>(boardService.get(id), HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<Flux<Board>> getList() {
        return new ResponseEntity<>(boardService.getList(), HttpStatusCode.valueOf(200));
    }
}
