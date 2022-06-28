package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;

import java.util.*;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberrepository = new MemoryMemberRepository();

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원이 있으면 안됩니다.
        //Optional<Member> result = memberrepository.findByName(member.getName());
        validateDuplicateMember(member); // 중복 회원 검증
        memberrepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        memberrepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberrepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberrepository.findById(memberId);
    }

}
