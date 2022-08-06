package example.practice.jpa.service.memer.repository;

import example.practice.jpa.service.memer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.team")
    List<Member> findAllUsingJoinFetch();

}
