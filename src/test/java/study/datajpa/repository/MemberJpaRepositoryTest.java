package study.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import study.datajpa.domain.Member;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {

	@Autowired
	MemberJpaRepository memberJpaRepository;
	
	@Test
	public void pasing() {
		Member member1 = new Member("AAA", 10);
		Member member2 = new Member("BBB", 10);
		Member member3 = new Member("CCC", 10);
		Member member4 = new Member("DDD", 10);
		Member member5 = new Member("EEE", 10);
		
		memberJpaRepository.save(member1);
		memberJpaRepository.save(member2);
		memberJpaRepository.save(member3);
		memberJpaRepository.save(member4);
		memberJpaRepository.save(member5);

		int age = 10;
		int offset = 0;
		int limit = 3;
		
		List<Member> result = memberJpaRepository.findByPage(age, offset, limit);
		
		assertThat(result.size()).isEqualTo(3);
		
	
	}
	
	@Test
	public void bulkUpdate() {
		memberJpaRepository.save(new Member("member1", 10));
		memberJpaRepository.save(new Member("member2", 10));
		memberJpaRepository.save(new Member("member3", 10));
		memberJpaRepository.save(new Member("member4", 10));
		memberJpaRepository.save(new Member("member5", 10));
		
		int resultCount = memberJpaRepository.bulkAgePlus(10);
		
		assertThat(resultCount).isEqualTo(5);
	}

}
