package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // test들은 서로 의존관계 없이 설계되어야 함
    // -> 하나의 test 끝날 때마다 리포지토리를 깔끔하게 지우기
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

//        Member result = repository.findById(member.getId()).get();
        Member result = repository.findById(member.getId()).orElse(null);

//        System.out.println("result = " + (result == member)); // 사실 이렇게 해도 되지만 직접 글자로 볼 수는 없음
//        Assertions.assertEquals(result, member); // org.junit.jupiter.api
        assertThat(member).isEqualTo(result); // org.assertj.core.api
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

//        Member result = repository.findByName("spring1").get();
        Member result = repository.findByName("spring1").orElse(null);

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
