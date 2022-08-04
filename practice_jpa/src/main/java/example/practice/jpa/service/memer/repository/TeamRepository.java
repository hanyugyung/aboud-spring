package example.practice.jpa.service.memer.repository;

import example.practice.jpa.service.memer.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
