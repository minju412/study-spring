package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL - select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); // findByNameAAndId(String name, Long id); 이런 식으로도 사용 가능
}
