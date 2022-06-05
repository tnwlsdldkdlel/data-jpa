package study.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import study.datajpa.domain.Member;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@PersistenceContext
	EntityManager em;

	@Test
	public void pasing() {
		Member member1 = new Member("AAA", 10);
		Member member2 = new Member("BBB", 10);
		Member member3 = new Member("CCC", 10);
		Member member4 = new Member("DDD", 10);
		Member member5 = new Member("EEE", 10);
		
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);
		memberRepository.save(member4);
		memberRepository.save(member5);

		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
		
		Slice<Member> page = memberRepository.findByAge(age, pageRequest);
		
		List<Member> content = page.getContent();
		Long totalElements = (long) page.getNumberOfElements(); // 전체개수 
		
		for(Member m : content) {
			System.out.println("member = " + m);
		}
		
		// System.out.println("totalElements = " + totalElements);
		
		assertThat(content.size()).isEqualTo(3); // 한 페이지당 보주는 개수
		assertThat(page.getNumber()).isEqualTo(0); // 페이지 번호
		// assertThat(page.getTotalPages()).isEqualTo(2); slice는 전체개수를 가져오지 못함 
		assertThat(page.isFirst()).isTrue(); // 첫 페이지인지 확인
		assertThat(page.hasNext()).isTrue(); // 다음 페이지가 있는지 확인 
	
	}
	
	@Test
	public void bulkUpdate() {
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));
		
		int resultCount = memberRepository.bulkAgePlus(10);
		
		// em.flush(); // 남아있는 데이터중에 변경되지 않은 부분 변경되록 
		// em.clear(); // 영속성컨테스트 비움 
		
		assertThat(resultCount).isEqualTo(5);
	}

}
