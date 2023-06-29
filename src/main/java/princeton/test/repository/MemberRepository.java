package princeton.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import princeton.test.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
