package example.practice.jpa.service.member.repository;

import example.practice.jpa.service.member.entity.Member;
import example.practice.jpa.service.member.entity.projection.MemberProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.team")
    List<Member> findAllUsingJoinFetch();

    @EntityGraph(attributePaths = "team")
    List<Member> findAll();

    @Query(nativeQuery = true
            , value = "select m.* from members m where m.id = :id")
    Member findByIdDirectly(@Param("id") Long id);

    @Query(nativeQuery = true
            , value = "select m.id, t.id as team_id from members m join teams t on m.team_id = t.id where m.id = :id")
    MemberProjection getMemberIdAndTeamIdById(@Param("id") Long id);
}
