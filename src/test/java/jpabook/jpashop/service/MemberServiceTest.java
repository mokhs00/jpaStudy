package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;



    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("HanSu");

        //when
        Long joinedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(joinedId));


    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("HanSu");

        Member member2 = new Member();
        member2.setName("HanSu");

        //when
        memberService.join(member1);
//        memberService.join(member2);

//        try {
//            memberService.join(member2);
//        }catch (IllegalStateException e){
//            return;
//        }

        //then

//        fail("예외가 발생해야 합니다.");
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }




}