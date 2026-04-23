
package org.td2.prog_3.Services;

import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.BadRequestException;

import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.Payment;
import org.td2.prog_3.Model.Transaction;
import org.td2.prog_3.Repository.PaymentRepository;
import org.td2.prog_3.Repository.ContributionRepository;
import org.td2.prog_3.Repository.MemberRepository;
import org.td2.prog_3.Repository.TransactionRepository;

import java.time.LocalDate;

@Service
public class PaymentServices {

    private PaymentRepository paymentRepository;
    private ContributionRepository contributionRepository;
    private MemberRepository memberRepository;
    private TransactionRepository transactionRepository;

    public PaymentServices(PaymentRepository paymentRepository,
                           ContributionRepository contributionRepository,
                           MemberRepository memberRepository,
                           TransactionRepository transactionRepository) {
        this.paymentRepository = paymentRepository;
        this.contributionRepository = contributionRepository;
        this.memberRepository = memberRepository;
        this.transactionRepository = transactionRepository;
    }

    public Payment makePayment(Double amount, Long memberId, Long contributionId, String mode) {
        if (amount <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }

        if (!memberRepository.existsById(memberId)) {
            throw new NotFoundException("Member not found with id: " + memberId);
        }

        if (contributionRepository.findByMemberId(memberId).isEmpty()) {
            throw new NotFoundException("Contribution not found for this member");
        }

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(LocalDate.now());
        payment.setMode(mode);
        payment.setMemberId(memberId);
        payment.setContributionId(contributionId);

        Payment savedPayment = paymentRepository.save(payment);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(LocalDate.now());
        transaction.setType("INCOME");
        transaction.setAccountId(1L);
        transactionRepository.save(transaction);
        transactionRepository.updateAccountBalance(1L, amount, "INCOME");

        return savedPayment;
    }
}
