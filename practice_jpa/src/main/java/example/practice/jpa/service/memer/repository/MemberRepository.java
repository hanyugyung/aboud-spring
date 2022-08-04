package example.practice.jpa.service.memer.repository;

import example.practice.jpa.service.memer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
