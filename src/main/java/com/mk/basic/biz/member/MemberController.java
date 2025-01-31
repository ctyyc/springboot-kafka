package com.mk.basic.biz.member;

import com.mk.basic.biz.member.model.Member;
import com.mk.basic.biz.member.model.MemberForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("")
    public ResponseEntity<List<Member>> findMembers() {
        List<Member> members = memberService.findMembers();
        return ResponseEntity.ok(members);
    }

    @PostMapping("/new")
    public ResponseEntity<Long> createMember(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        Long id = memberService.join(member);

        return ResponseEntity.ok(id);
    }
}