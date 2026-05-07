package org.td2.exam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.exam.Model.Member;
import org.td2.exam.Model.Payment;
import org.td2.exam.Service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<List<Member>> createMembers(@RequestBody List<Member> members) {
        List<Member> created = memberService.createMembers(members);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<List<Payment>> createPayments(
            @PathVariable String id,
            @RequestBody List<Payment> payments) {
        List<Payment> created = memberService.createPayments(id, payments);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}