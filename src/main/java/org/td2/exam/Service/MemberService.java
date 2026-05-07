package org.td2.exam.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.td2.exam.Exception.BusinessException;
import org.td2.exam.Model.Member;
import org.td2.exam.Model.Payment;
import org.td2.exam.Repository.MemberRepository;
import org.td2.exam.Repository.PaymentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, PaymentRepository paymentRepository) {
        this.memberRepository = memberRepository;
        this.paymentRepository = paymentRepository;
    }

    public List<Member> createMembers(List<Member> members) {
        for (Member m : members) {
            if (m.getId() == null) {
                m.setId("M" + UUID.randomUUID().toString().substring(0, 8));
            }
            if (m.getDateAdhesion() == null) {
                m.setDateAdhesion(LocalDate.now());
            }
            memberRepository.insert(m);
        }
        return members;
    }

    public List<Payment> createPayments(String memberId, List<Payment> payments) {
        if (!memberRepository.existsById(memberId)) {
            throw new BusinessException("Member not found");
        }

        for (Payment p : payments) {
            p.setMemberId(memberId);
            if (p.getDatePaiement() == null) {
                p.setDatePaiement(LocalDate.now());
            }
            paymentRepository.insert(p);
        }
        return payments;
    }
}