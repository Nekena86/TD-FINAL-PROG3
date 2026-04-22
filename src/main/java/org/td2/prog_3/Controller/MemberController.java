package org.td2.prog_3.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.prog_3.Services.MemberServices;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class MemberController {

    private MemberServices memberService;

    public MemberController(MemberServices memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<Map<String, Object>> createMember(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = memberService.createMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<Map<String, Object>> getMember(@PathVariable Long memberId) {
        Map<String, Object> response = memberService.getMember(memberId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/collectivities/{collectivityId}/members")
    public ResponseEntity<List<Map<String, Object>>> getMembersByCollectivity(@PathVariable Long collectivityId) {
        List<Map<String, Object>> response = memberService.getMembersByCollectivity(collectivityId);
        return ResponseEntity.ok(response);
    }
}