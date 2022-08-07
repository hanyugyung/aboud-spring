package example.practice.jpa.service.memer.repository;

import example.practice.jpa.service.memer.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t join fetch t.memberList")
    List<Team> findAllUsingJoinFetch();

    @Query("select distinct t from Team t join fetch t.memberList")
    List<Team> findAllUsingJoinFetchDistinct();
}
