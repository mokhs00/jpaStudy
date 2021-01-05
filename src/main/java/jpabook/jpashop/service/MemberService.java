package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// @Transactional(readOnly = true)
// 조회만 하는 경우 성능이 최적화됨 > 플러시, 더티 체킹 x 등등.. || db에 따라 읽기모드 등. 할당되는 리소스가 다름
@Transactional(readOnly = true)
@RequiredArgsConstructor // > final 붙은 필요한 객체들의 생성자를 생성
public class MemberService {

//    @Autowired
//    private MemberRepository memberRepository;
    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> byName = memberRepository.findByName(member.getName());
        if (!byName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
