package princeton.test.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import princeton.test.member.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByUsername(String username);
}
