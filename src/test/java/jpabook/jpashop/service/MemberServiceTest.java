package jpabook.jpashop.service;

import org.junit.Test;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("최재민");

        Member member2 = new Member();
        member2.setName("최재민");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생해야 함");
    }


}