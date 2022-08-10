package example.practice.jpa.service.member.repository;

import example.practice.jpa.service.member.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
