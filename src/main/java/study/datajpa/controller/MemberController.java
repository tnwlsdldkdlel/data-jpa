package study.datajpa.controller;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.datajpa.domain.Member;
import study.datajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberRepository memberRepository;
	
	@PostConstruct
	public void init() {
		for(int i = 0 ; i < 100 ; i ++) {
			memberRepository.save(new Member("member"+i,i));
		}
	}
	
	@GetMapping("/members/{id}")
	public String findMember(@PathVariable("id") Long id) {
		Member member = memberRepository.findById(id).get();
		
		return member.getUsername();
	}
	
	@GetMapping("/members2/{id}")
	public String findMember2(@PathVariable("id") Member member) {
		System.out.println(member.getUsername());
		return member.getUsername();
	}
	
	@GetMapping("/members")
	public Page<Member> list(Pageable pageable) {
		return memberRepository.findAll(pageable);
	}
	
}
