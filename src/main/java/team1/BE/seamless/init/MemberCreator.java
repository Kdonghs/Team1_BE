package team1.BE.seamless.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team1.BE.seamless.DTO.MemberRequestDTO;
import team1.BE.seamless.service.MemberService;

@Component
public class MemberCreator {

    private final MemberService memberService;

    @Autowired
    public MemberCreator(MemberService memberService) {
        this.memberService = memberService;
    }

    public void creator() {

        MemberRequestDTO.CreateMember member1 = new MemberRequestDTO.CreateMember("ex1@gmail.com","cCeJvA99H7bV2ctvVIpM4Bh3ZJvawh3JnX3tREWGtNA=","순호");
        memberService.createMember(member1);

        MemberRequestDTO.CreateMember member2 = new MemberRequestDTO.CreateMember("ex2@gmail.com","cCeJvA99H7bV2ctvVIpM4Bh3ZJvawh3JnX3tREWGtNA=","동혁");
        memberService.createMember(member2);

        MemberRequestDTO.CreateMember member3 = new MemberRequestDTO.CreateMember("ex3@gmail.com","cCeJvA99H7bV2ctvVIpM4Bh3ZJvawh3JnX3tREWGtNA=","도헌");
        memberService.createMember(member3);
    }

}
